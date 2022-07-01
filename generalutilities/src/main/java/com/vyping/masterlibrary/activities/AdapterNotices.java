package com.vyping.masterlibrary.activities;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.smarteist.autoimageslider.SliderViewAdapter;
import com.vyping.masterlibrary.Animations.MyAnimation;
import com.vyping.masterlibrary.BR;
import com.vyping.masterlibrary.R;
import com.vyping.masterlibrary.databinding.NoticeHolderPreviewBinding;
import com.vyping.masterlibrary.models.notices.Notice;

import java.util.ArrayList;

public class AdapterNotices extends SliderViewAdapter<AdapterNotices.SliderAdapterViewHolder> {

    public Context context;
    public Interfase interfase;
    public ArrayList<Notice> arrayNotices;


    // ----- SetUp ----- //

    public AdapterNotices(Context context, Interfase interfase) {

        this.context = context;
        this.interfase = interfase;
        this.arrayNotices = new ArrayList<>();
    }

    public void restartAdapter() {

        this.arrayNotices = new ArrayList<>();
        notifyDataSetChanged();
    }

    @Override
    public SliderAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {

        NoticeHolderPreviewBinding binding =  DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.notice_holder_preview, parent, false);

        return new SliderAdapterViewHolder(binding);
    }

    @Override @SuppressLint("ClickableViewAccessibility")
    public void onBindViewHolder(@NonNull SliderAdapterViewHolder viewHolder, int position) {

        final Notice notice = arrayNotices.get(position);

        GestureDetector gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {

                return true;
            }

            public void DummyVoid() {
            }
        });

        viewHolder.binding.setVariable(BR.notice, notice);
        viewHolder.binding.IvMenImage.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                int action = motionEvent.getActionMasked();

                if (action == MotionEvent.ACTION_DOWN) {

                    new MyAnimation().ScaleAmpliate(viewHolder.binding.IvMenImage, 100, 0.9f, 1.1f, 0.9f, 1.1f);

                } else if (action == MotionEvent.ACTION_OUTSIDE) {

                    new MyAnimation().ScaleAmpliate(viewHolder.binding.IvMenImage, 100, 1.05f, 1.0f, 1.05f, 1.0f);

                } else if (action == MotionEvent.ACTION_CANCEL) {

                    new MyAnimation().ScaleAmpliate(viewHolder.binding.IvMenImage, 100, 1.1f, 1.0f, 1.1f, 1.0f);

                } else if (action == MotionEvent.ACTION_UP) {

                    new MyAnimation().ScaleAmpliate(viewHolder.binding.IvMenImage, 100, 1.1f, 1.0f, 1.1f, 1.0f);
                }

                if (gestureDetector.onTouchEvent(motionEvent)) {

                    interfase.Selected(position, notice);
                }

                return true;
            }
        });
        viewHolder.binding.executePendingBindings();
    }

    public static class SliderAdapterViewHolder extends ViewHolder {

       NoticeHolderPreviewBinding binding;

        public SliderAdapterViewHolder(@NonNull NoticeHolderPreviewBinding binding) {

            super(binding.getRoot());

            this.binding = binding;
        }
    }

    @Override
    public int getCount() {

        if (arrayNotices == null) {

            return 0;

        } else {

            return arrayNotices.size();
        }
    }


    // ----- Methods ----- //

    public void insertByLoad(@NonNull final Notice notice) {

        int position = arrayNotices.size();
        arrayNotices.add(position, notice);
        notifyDataSetChanged();
    }

    public void insertByChange(@NonNull final Notice notice) {

        int position = setPosition(notice);
        arrayNotices.add(position, notice);
        notifyDataSetChanged();
    }

    public void modifyNotice(@NonNull final Notice notice) {

        String idNotice = notice.Id;
        int position = getPositionOnArray(idNotice);
        arrayNotices.set(position, notice);
        notifyDataSetChanged();
    }

    public void removeNotice(final String idNotice) {

        int position = getPositionOnArray(idNotice);

        if (position != -1) {

            arrayNotices.remove(position);
            notifyDataSetChanged();
        }
    }


    // ----- Tools ----- //

    private boolean existNotice(@NonNull final Notice notice) {

        boolean exist = FALSE;

        for (final Notice compareNotice : arrayNotices) {

            if (notice.Id.equals(compareNotice.Id)) {

                exist = TRUE;
                break;
            }
        }

        return exist;
    }

    private int setPosition(@NonNull final Notice notice) {

        long arraySize = arrayNotices.size();
        int returnPosition = 0;

        if (!arrayNotices.isEmpty()) {

            for (int position = 0; position < arraySize; position ++) {

                String compareNotice = String.valueOf(arrayNotices.get(position).Id);
                int compareMarge = notice.Id.compareTo(compareNotice);

                if (compareMarge < 0) {

                    returnPosition = position;
                    break;

                } else if (compareMarge == 0) {

                    returnPosition = position + 1;

                } else if (compareMarge > 0) {

                    if (position == arraySize - 1) {

                        returnPosition = position + 1;

                    } else {

                        String nextNotice = String.valueOf(arrayNotices.get(position).Id);
                        int nextMarge = notice.Id.compareTo(nextNotice);

                        if (nextMarge < 0) {

                            returnPosition = position + 1;
                            break;
                        }
                    }
                }
            }
        }

        return returnPosition;
    }

    public int getPosition(@NonNull Notice notice) {

        int position = -1, returnPosition = arrayNotices.size();

        if (returnPosition != 0) {

            for (final Notice compareNotice : arrayNotices) {

                position = position + 1;

                if (notice.Id.equals(compareNotice.Id)) {

                    returnPosition = position;
                    break;
                }
            }
        }

        return returnPosition;
    }

    public int getPositionOnArray(final String IdNotice) {

        int position = -1, noticePosition = -1;

        for (final Notice notice : arrayNotices) {

            position = position + 1;

            if (IdNotice.equals(notice.Id)) {

                noticePosition = position;
                break;
            }
        }

        return noticePosition;
    }

    public Notice getNotice(int position) {

        return arrayNotices.get(position);
    }


    // ----- Interface ----- //

    public interface Interfase {

        void Selected(int position, Notice notice);
    }
}
