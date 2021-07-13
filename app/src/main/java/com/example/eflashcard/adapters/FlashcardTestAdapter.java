package com.example.eflashcard.adapters;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eflashcard.R;
import com.example.eflashcard.models.Word;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class FlashcardTestAdapter extends RecyclerView.Adapter<FlashcardTestAdapter.ViewHolder> {

    List<Word> words;
    Context context;

    public FlashcardTestAdapter(List<Word> words, Context context)
    {
        this.words = words;
        this.context = context;
    }

    @NonNull
    @Override
    public FlashcardTestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_test, parent, false);
        return new FlashcardTestAdapter.ViewHolder(card);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FlashcardTestAdapter.ViewHolder holder, int position) {
        AtomicBoolean mIsBackVisible = new AtomicBoolean(false);

        Word word = words.get(position);
        FrameLayout card = holder.card;

        // Find views ----------------------------------------------------
        View mCardBackLayout = card.findViewById(R.id.card_back_test);
        View mCardFrontLayout = card.findViewById(R.id.card_front_test);
        Button bHint = (Button) mCardFrontLayout.findViewById(R.id.hint);
        ImageView ivImage = (ImageView) mCardFrontLayout.findViewById(R.id.pic);
        TextView tvQuestion = (TextView) mCardFrontLayout.findViewById(R.id.question);
        EditText etAnswer = (EditText) mCardFrontLayout.findViewById(R.id.answer);
        Button bSubmit = (Button) mCardFrontLayout.findViewById(R.id.submit);
        TextView tvDefinition = (TextView) mCardBackLayout.findViewById(R.id.definition);
        ImageView ivPic = (ImageView) mCardBackLayout.findViewById(R.id.pic);
        Button bPronounce = (Button) mCardBackLayout.findViewById(R.id.pronounce);
        ImageView backgroundTest = (ImageView) mCardBackLayout.findViewById(R.id.background_back_card_test);
        // ---------------------------------------------------------------

        // Set views -----------------------------------------------------
        bHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer player = MediaPlayer.create(context, word.getAudioID(context));
                player.start();
            }
        });
        ivImage.setImageResource(word.getImageID(context));
        tvQuestion.setText(word.getDefinition());
        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer = etAnswer.getText().toString().toLowerCase();
                if (!answer.equals("")) {
                    if (answer.equals(word.getWord().toLowerCase())) {
                        backgroundTest.setImageResource(R.drawable.background_correct);
                    }
                    else {
                        backgroundTest.setImageResource(R.drawable.background_incorrect);
                    }
                    holder.isSubmit = true;
                    etAnswer.setText("");
                    card.callOnClick();
                }
            }
        });
        tvDefinition.setText(word.getDefinition());
        ivPic.setImageResource(word.getImageID(context));
        bPronounce.setEnabled(false);
        bPronounce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer player = MediaPlayer.create(context, word.getAudioID(context));
                player.start();
            }
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
            if (holder.isSubmit) {
                if (!mIsBackVisible.get()) {
                    mSetRightOut.setTarget(mCardFrontLayout);
                    mSetLeftIn.setTarget(mCardBackLayout);
                    mSetRightOut.start();
                    mSetLeftIn.start();
                    mIsBackVisible.set(true);
                    bPronounce.setEnabled(true);
                    etAnswer.setEnabled(false);
                } else {
                    mSetRightOut.setTarget(mCardBackLayout);
                    mSetLeftIn.setTarget(mCardFrontLayout);
                    mSetRightOut.start();
                    mSetLeftIn.start();
                    mIsBackVisible.set(false);
                    bPronounce.setEnabled(false);
                    etAnswer.setEnabled(true);
                }
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
        boolean isSubmit = false;

        ViewHolder(View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.card_test);
        }
    }
}
