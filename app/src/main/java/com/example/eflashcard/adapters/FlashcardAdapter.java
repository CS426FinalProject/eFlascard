package com.example.eflashcard.adapters;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eflashcard.R;
import com.example.eflashcard.models.Word;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class FlashcardAdapter extends RecyclerView.Adapter<FlashcardAdapter.ViewHolder> {
    List<Word> words;
    Context context;

    public FlashcardAdapter(List<Word> words, Context context)
    {
        this.words = words;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new ViewHolder(card);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AtomicBoolean mIsBackVisible = new AtomicBoolean(false);

        Word word = words.get(position);
        FrameLayout card = holder.card;

        // Find views ----------------------------------------------------
        View mCardBackLayout = card.findViewById(R.id.card_back);
        View mCardFrontLayout = card.findViewById(R.id.card_front);
        TextView tvDefinition = mCardBackLayout.findViewById(R.id.definition);
        TextView tvWord = mCardFrontLayout.findViewById(R.id.word);
        tvWord.setText(word.getWord());
        tvDefinition.setText(word.getDefinition());
        ImageView image = mCardBackLayout.findViewById(R.id.pic);
        image.setImageResource(word.getImageID(context));
        Button pronounce = mCardBackLayout.findViewById(R.id.pronounce);
        pronounce.setEnabled(false);
        pronounce.setOnClickListener(v -> {
            MediaPlayer player = MediaPlayer.create(context, word.getAudioID(context));
            player.start();
        });
        // ---------------------------------------------------------------


        // Load animations
        AnimatorSet mSetRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.out_animation);
        AnimatorSet mSetLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.in_animation);
        // ---------------------------------------------------------------

        // Change camera distance ----------------------------------------
        int distance = 8000;
        float scale = context.getResources().getDisplayMetrics().density * distance;
        mCardFrontLayout.setCameraDistance(scale);
        mCardBackLayout.setCameraDistance(scale);
        // ---------------------------------------------------------------

        card.setOnClickListener(v -> {
            if (!mIsBackVisible.get()) {
                mSetRightOut.setTarget(mCardFrontLayout);
                mSetLeftIn.setTarget(mCardBackLayout);
                mSetRightOut.start();
                mSetLeftIn.start();
                mIsBackVisible.set(true);
                pronounce.setEnabled(true);
            } else {
                mSetRightOut.setTarget(mCardBackLayout);
                mSetLeftIn.setTarget(mCardFrontLayout);
                mSetRightOut.start();
                mSetLeftIn.start();
                mIsBackVisible.set(false);
                pronounce.setEnabled(false);
            }
        });
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        FrameLayout card;

        ViewHolder(View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.card);
        }
    }
}
