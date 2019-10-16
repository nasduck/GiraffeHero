package com.zoopark.rv.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class BaseItemAnimator extends SimpleItemAnimator {

    private static TimeInterpolator sDefaultInterpolator;

    // 新增动画的相关配置
    private long mAddAnimDelay;
    private TimeInterpolator mAddAnimInterpolator;
    private AnimatorListenerAdapter mAddAnimListener;

    // 移除动画的相关配置
    private long mRemoveAnimDelay;
    private TimeInterpolator mRemoveAnimInterpolator;
    private AnimatorListenerAdapter mRemoveAnimListener;

    // 要进行动画的ViewHolder
    private ArrayList<RecyclerView.ViewHolder> mPendingRemovals = new ArrayList();
    private ArrayList<RecyclerView.ViewHolder> mPendingAdditions = new ArrayList();
    private ArrayList<BaseItemAnimator.MoveInfo> mPendingMoves = new ArrayList();
    private ArrayList<BaseItemAnimator.ChangeInfo> mPendingChanges = new ArrayList();


    private ArrayList<ArrayList<RecyclerView.ViewHolder>> mAdditionsList = new ArrayList();
    private ArrayList<ArrayList<BaseItemAnimator.MoveInfo>> mMovesList = new ArrayList();
    private ArrayList<ArrayList<BaseItemAnimator.ChangeInfo>> mChangesList = new ArrayList();

    // 正在进行动画的ViewHolder
    private ArrayList<RecyclerView.ViewHolder> mAddAnimations = new ArrayList();
    private ArrayList<RecyclerView.ViewHolder> mMoveAnimations = new ArrayList();
    private ArrayList<RecyclerView.ViewHolder> mRemoveAnimations = new ArrayList();
    private ArrayList<RecyclerView.ViewHolder> mChangeAnimations = new ArrayList();

    // item的更新信息
    private static class ChangeInfo {
        public RecyclerView.ViewHolder oldHolder;
        public RecyclerView.ViewHolder newHolder;
        public int fromX;
        public int fromY;
        public int toX;
        public int toY;

        private ChangeInfo(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder) {
            this.oldHolder = oldHolder;
            this.newHolder = newHolder;
        }

        ChangeInfo(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromX, int fromY, int toX, int toY) {
            this(oldHolder, newHolder);
            this.fromX = fromX;
            this.fromY = fromY;
            this.toX = toX;
            this.toY = toY;
        }

        public String toString() {
            return "ChangeInfo{oldHolder=" + this.oldHolder + ", newHolder=" + this.newHolder + ", fromX=" + this.fromX + ", fromY=" + this.fromY + ", toX=" + this.toX + ", toY=" + this.toY + '}';
        }
    }

    // item的移动信息
    private static class MoveInfo {
        public RecyclerView.ViewHolder holder;
        public int fromX;
        public int fromY;
        public int toX;
        public int toY;

        MoveInfo(RecyclerView.ViewHolder holder, int fromX, int fromY, int toX, int toY) {
            this.holder = holder;
            this.fromX = fromX;
            this.fromY = fromY;
            this.toX = toX;
            this.toY = toY;
        }
    }

    // 构造函数
    public BaseItemAnimator() {
        this.mAddAnimDelay = 0;
        this.mAddAnimInterpolator = new LinearInterpolator();
        this.mAddAnimListener = null;

        this.mRemoveAnimDelay = 0;
        this.mRemoveAnimInterpolator = new LinearInterpolator();
        this.mRemoveAnimListener = null;
    }

    /** 动画的控制 **********************************************************************************/

    // 真正控制执行动画的地方
    public void runPendingAnimations() {
        // 判断是否有正在进行相应的动画操作
        boolean removalsPending = !this.mPendingRemovals.isEmpty();
        boolean movesPending = !this.mPendingMoves.isEmpty();
        boolean changesPending = !this.mPendingChanges.isEmpty();
        boolean additionsPending = !this.mPendingAdditions.isEmpty();
        // 如果有正在进行的动画操作
        if (removalsPending || movesPending || additionsPending || changesPending) {
            // 判断移除动画是否有正在进行的
            Iterator iteratorRemovals = this.mPendingRemovals.iterator();
            while(iteratorRemovals.hasNext()) {
                RecyclerView.ViewHolder holder = (RecyclerView.ViewHolder)iteratorRemovals.next();
                // 调用移除动画
                this.animateRemoveImpl(holder);
            }
            // 清除要进行移除动画的list
            this.mPendingRemovals.clear();

            final ArrayList moves;
            final ArrayList changes;
            final ArrayList additions;
            Runnable adder;
            // 判断是否有移动的动画要执行
            if (movesPending) {
                moves = new ArrayList();
                moves.addAll(this.mPendingMoves);
                this.mMovesList.add(moves);
                this.mPendingMoves.clear();
                adder = new Runnable() {
                    public void run() {
                        Iterator iteratorMoves = moves.iterator();

                        while(iteratorMoves.hasNext()) {
                            BaseItemAnimator.MoveInfo moveInfo = (BaseItemAnimator.MoveInfo)iteratorMoves.next();
                            // 调用移动动画
                            BaseItemAnimator.this.animateMoveImpl(moveInfo.holder, moveInfo.fromX, moveInfo.fromY, moveInfo.toX, moveInfo.toY);
                        }

                        moves.clear();
                        BaseItemAnimator.this.mMovesList.remove(moves);
                    }
                };
                // 如果有要移除动画的，则延迟删除动画的时间后再运行移动的动画；如果没有，则运行移动的动画
                if (removalsPending) {
                    View view = ((BaseItemAnimator.MoveInfo)moves.get(0)).holder.itemView;
                    ViewCompat.postOnAnimationDelayed(view, adder, this.getRemoveDuration());
                } else {
                    adder.run();
                }
            }

            // 判断是否有更新的动画要执行
            if (changesPending) {
                changes = new ArrayList();
                changes.addAll(this.mPendingChanges);
                this.mChangesList.add(changes);
                this.mPendingChanges.clear();
                adder = new Runnable() {
                    public void run() {
                        Iterator iteratorChanges = changes.iterator();

                        while(iteratorChanges.hasNext()) {
                            BaseItemAnimator.ChangeInfo change = (BaseItemAnimator.ChangeInfo)iteratorChanges.next();
                            // 调用更新动画
                            BaseItemAnimator.this.animateChangeImpl(change);
                        }

                        changes.clear();
                        BaseItemAnimator.this.mChangesList.remove(changes);
                    }
                };
                if (removalsPending) {
                    RecyclerView.ViewHolder holder = ((BaseItemAnimator.ChangeInfo)changes.get(0)).oldHolder;
                    ViewCompat.postOnAnimationDelayed(holder.itemView, adder, this.getRemoveDuration());
                } else {
                    adder.run();
                }
            }

            // 判断是否有新增的动画要执行
            if (additionsPending) {
                additions = new ArrayList();
                additions.addAll(this.mPendingAdditions);
                this.mAdditionsList.add(additions);
                this.mPendingAdditions.clear();
                adder = new Runnable() {
                    public void run() {
                        Iterator iteratorAdditions = additions.iterator();

                        while(iteratorAdditions.hasNext()) {
                            RecyclerView.ViewHolder holder = (RecyclerView.ViewHolder)iteratorAdditions.next();
                            // 调用新建动画
                            BaseItemAnimator.this.animateAddImpl(holder);
                        }

                        additions.clear();
                        BaseItemAnimator.this.mAdditionsList.remove(additions);
                    }
                };
                // 没有其他类型的动画在进行时，开始新增动画；如果有，延迟开始新增动画
                if (!removalsPending && !movesPending && !changesPending) {
                    adder.run();
                } else {
                    long removeDuration = removalsPending ? this.getRemoveDuration() : 0L;
                    long moveDuration = movesPending ? this.getMoveDuration() : 0L;
                    long changeDuration = changesPending ? this.getChangeDuration() : 0L;
                    long totalDelay = removeDuration + Math.max(moveDuration, changeDuration);
                    View view = ((RecyclerView.ViewHolder)additions.get(0)).itemView;
                    ViewCompat.postOnAnimationDelayed(view, adder, totalDelay);
                }
            }

        }
    }

    /** 移除动画 ************************************************************************************/

    // Item移除动画的初始化
    @Override
    public boolean animateRemove(RecyclerView.ViewHolder holder) {
        this.resetAnimation(holder);
        this.mPendingRemovals.add(holder);
        return true;
    }

    // 移除动画的动画设置
    private void animateRemoveImpl(final RecyclerView.ViewHolder holder) {
        // 获取对应ViewHolder的itemView
        final View view = holder.itemView;
        // 动画持有者
        final ViewPropertyAnimator animation = view.animate();
        // 在正在进行移除动画的ViewHolder的list中添加进行移除动画的ViewHolder
        this.mRemoveAnimations.add(holder);
        // 移除的动画设置
        removeAnimation(animation)
                .setDuration(this.getRemoveDuration())
                .setStartDelay(mRemoveAnimDelay)
                .setInterpolator(mRemoveAnimInterpolator)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationCancel(Animator animator) {
                        super.onAnimationCancel(animator);
                        if (mRemoveAnimListener != null) mRemoveAnimListener.onAnimationCancel(animator);
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        super.onAnimationEnd(animator);
                        // 结束后将属性还原
                        animation.setListener(null);
                        animationEnd(view);
                        // 可以在onRemoveFinished中实现移除动画结束时的逻辑
                        BaseItemAnimator.this.dispatchRemoveFinished(holder);
                        // 进行完动画后，将ViewHolder在对应的list中移除
                        BaseItemAnimator.this.mRemoveAnimations.remove(holder);
                        // 完全完成移除动画后的操作
                        BaseItemAnimator.this.dispatchFinishedWhenDone();
                        if (mRemoveAnimListener != null) mRemoveAnimListener.onAnimationEnd(animator);
                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {
                        super.onAnimationRepeat(animator);
                        if (mRemoveAnimListener != null) mRemoveAnimListener.onAnimationRepeat(animator);
                    }

                    @Override
                    public void onAnimationStart(Animator animator) {
                        super.onAnimationStart(animator);
                        // 可以在onRemoveStarting中实现移除动画开始时的逻辑
                        BaseItemAnimator.this.dispatchRemoveStarting(holder);
                        if (mRemoveAnimListener != null) mRemoveAnimListener.onAnimationStart(animator);
                    }

                    @Override
                    public void onAnimationPause(Animator animator) {
                        super.onAnimationPause(animator);
                        if (mRemoveAnimListener != null) mRemoveAnimListener.onAnimationPause(animator);
                    }

                    @Override
                    public void onAnimationResume(Animator animator) {
                        super.onAnimationResume(animator);
                        if (mRemoveAnimListener != null) mRemoveAnimListener.onAnimationResume(animator);
                    }
                }).start();
    }

    /** 添加动画 ************************************************************************************/

    // item添加动画的初始化
    @Override
    public boolean animateAdd(RecyclerView.ViewHolder holder) {
        // 先结束对应holder的动画
        this.resetAnimation(holder);
        // 设置对应holder的itemView初始化的状态
        addAnimationInit(holder);
        // mPendingAdditions添加进行添加动画的ViewHolder
        this.mPendingAdditions.add(holder);
        return true;
    }

    // 添加动画的动画设置
    void animateAddImpl(final RecyclerView.ViewHolder holder) {
        final View view = holder.itemView;
        final ViewPropertyAnimator animation = view.animate();
        this.mAddAnimations.add(holder);
        addAnimation(animation)
                .setDuration(this.getAddDuration())
                .setStartDelay(mAddAnimDelay)
                .setInterpolator(mAddAnimInterpolator)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationCancel(Animator animator) {
                        super.onAnimationCancel(animator);
                        animationEnd(view);
                        if (mAddAnimListener != null) mAddAnimListener.onAnimationCancel(animator);
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        super.onAnimationEnd(animator);
                        animation.setListener(null);
                        BaseItemAnimator.this.dispatchAddFinished(holder);
                        BaseItemAnimator.this.mAddAnimations.remove(holder);
                        BaseItemAnimator.this.dispatchFinishedWhenDone();
                        if (mAddAnimListener != null) mAddAnimListener.onAnimationEnd(animator);
                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {
                        super.onAnimationRepeat(animator);
                        if (mAddAnimListener != null) mAddAnimListener.onAnimationRepeat(animator);
                    }

                    @Override
                    public void onAnimationStart(Animator animator) {
                        super.onAnimationStart(animator);
                        BaseItemAnimator.this.dispatchAddStarting(holder);
                        if (mAddAnimListener != null) mAddAnimListener.onAnimationStart(animator);
                    }

                    @Override
                    public void onAnimationPause(Animator animator) {
                        super.onAnimationPause(animator);
                        if (mAddAnimListener != null) mAddAnimListener.onAnimationPause(animator);
                    }

                    @Override
                    public void onAnimationResume(Animator animator) {
                        super.onAnimationResume(animator);
                        if (mAddAnimListener != null) mAddAnimListener.onAnimationResume(animator);
                    }
                }).start();
    }

    /** item的移动动画 ******************************************************************************/

    // 移动动画的初始化
    public boolean animateMove(RecyclerView.ViewHolder holder, int fromX, int fromY, int toX, int toY) {
        View view = holder.itemView;
        fromX += (int)holder.itemView.getTranslationX();
        fromY += (int)holder.itemView.getTranslationY();
        // 先结束对应holder的动画
        this.resetAnimation(holder);

        int deltaX = toX - fromX;
        int deltaY = toY - fromY;
        // 如果偏移量为0，则直接结束动画，不为零，在对应方向上设置偏移量
        if (deltaX == 0 && deltaY == 0) {
            this.dispatchMoveFinished(holder);
            return false;
        } else {
            if (deltaX != 0) {
                view.setTranslationX((float)(-deltaX));
            }

            if (deltaY != 0) {
                view.setTranslationY((float)(-deltaY));
            }
            // 在要进行移动动画的list中添加对应的ViewHolder以及坐标信息
            this.mPendingMoves.add(new BaseItemAnimator.MoveInfo(holder, fromX, fromY, toX, toY));
            return true;
        }
    }

    // 出现其它动画时，其余的item的移动动画的动画设置
    void animateMoveImpl(final RecyclerView.ViewHolder holder, int fromX, int fromY, int toX, int toY) {
        // 获取对应的ViewHolder的itemView
        final View view = holder.itemView;
        // 移动距离的差值,如果有差值，就使对应的itemView移动到应该在的原来的0.0位置
        final int deltaX = toX - fromX;
        final int deltaY = toY - fromY;
        if (deltaX != 0) {
            view.animate().translationX(0.0F);
        }

        if (deltaY != 0) {
            view.animate().translationY(0.0F);
        }

        final ViewPropertyAnimator animation = view.animate();
        this.mMoveAnimations.add(holder);
        animation.setDuration(this.getMoveDuration()).setListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                // 开始移动动画时的操作
                BaseItemAnimator.this.dispatchMoveStarting(holder);
            }

            public void onAnimationCancel(Animator animator) {
                // 如果动画中途取消，则直接达到对应的动画结果
                if (deltaX != 0) {
                    view.setTranslationX(0.0F);
                }

                if (deltaY != 0) {
                    view.setTranslationY(0.0F);
                }

            }

            public void onAnimationEnd(Animator animator) {
                animation.setListener(null);
                // 结束移动动画时的操作
                BaseItemAnimator.this.dispatchMoveFinished(holder);
                BaseItemAnimator.this.mMoveAnimations.remove(holder);
                // 完全完成动画时的操作
                BaseItemAnimator.this.dispatchFinishedWhenDone();
            }
        }).start();
    }

    /** item的更新动画 ******************************************************************************/

    // 更新动画的初始化
    @Override
    public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromX, int fromY, int toX, int toY) {
        if (oldHolder == newHolder) {
            return this.animateMove(oldHolder, fromX, fromY, toX, toY);
        } else {
            float prevTranslationX = oldHolder.itemView.getTranslationX();
            float prevTranslationY = oldHolder.itemView.getTranslationY();
            float prevAlpha = oldHolder.itemView.getAlpha();
            this.resetAnimation(oldHolder);
            int deltaX = (int)((float)(toX - fromX) - prevTranslationX);
            int deltaY = (int)((float)(toY - fromY) - prevTranslationY);
            oldHolder.itemView.setTranslationX(prevTranslationX);
            oldHolder.itemView.setTranslationY(prevTranslationY);
            oldHolder.itemView.setAlpha(prevAlpha);
            if (newHolder != null) {
                this.resetAnimation(newHolder);
                newHolder.itemView.setTranslationX((float)(-deltaX));
                newHolder.itemView.setTranslationY((float)(-deltaY));
                newHolder.itemView.setAlpha(0.0F);
            }

            this.mPendingChanges.add(new BaseItemAnimator.ChangeInfo(oldHolder, newHolder, fromX, fromY, toX, toY));
            return true;
        }
    }

    // 更新动画的动画设置
    void animateChangeImpl(final BaseItemAnimator.ChangeInfo changeInfo) {
        RecyclerView.ViewHolder holder = changeInfo.oldHolder;
        final View view = holder == null ? null : holder.itemView;
        RecyclerView.ViewHolder newHolder = changeInfo.newHolder;
        final View newView = newHolder != null ? newHolder.itemView : null;

        final ViewPropertyAnimator oldViewAnimation;
        final ViewPropertyAnimator newViewAnimation;
        // 如果旧的ViewHolder不为null
        if (view != null) {
            oldViewAnimation = view.animate().setDuration(this.getChangeDuration());
            this.mChangeAnimations.add(changeInfo.oldHolder);
            oldViewAnimation.translationX((float)(changeInfo.toX - changeInfo.fromX));
            oldViewAnimation.translationY((float)(changeInfo.toY - changeInfo.fromY));
            oldViewAnimation.alpha(0.0F).setListener(new AnimatorListenerAdapter() {
                public void onAnimationStart(Animator animator) {
                    BaseItemAnimator.this.dispatchChangeStarting(changeInfo.oldHolder, true);
                }

                public void onAnimationEnd(Animator animator) {
                    oldViewAnimation.setListener(null);
                    view.setAlpha(1.0F);
                    view.setTranslationX(0.0F);
                    view.setTranslationY(0.0F);
                    BaseItemAnimator.this.dispatchChangeFinished(changeInfo.oldHolder, true);
                    BaseItemAnimator.this.mChangeAnimations.remove(changeInfo.oldHolder);
                    BaseItemAnimator.this.dispatchFinishedWhenDone();
                }
            }).start();
        }

        // 如果新的ViewHolder不为null
        if (newView != null) {
            newViewAnimation = newView.animate();
            this.mChangeAnimations.add(changeInfo.newHolder);
            newViewAnimation.translationX(0.0F)
                    .translationY(0.0F)
                    .setDuration(this.getChangeDuration())
                    .alpha(1.0F)
                    .setListener(new AnimatorListenerAdapter() {
                        public void onAnimationStart(Animator animator) {
                            BaseItemAnimator.this.dispatchChangeStarting(changeInfo.newHolder, false);
                        }

                        public void onAnimationEnd(Animator animator) {
                            newViewAnimation.setListener(null);
                            newView.setAlpha(1.0F);
                            newView.setTranslationX(0.0F);
                            newView.setTranslationY(0.0F);
                            BaseItemAnimator.this.dispatchChangeFinished(changeInfo.newHolder, false);
                            BaseItemAnimator.this.mChangeAnimations.remove(changeInfo.newHolder);
                            BaseItemAnimator.this.dispatchFinishedWhenDone();
                        }
                    }).start();
        }
    }

    /** 动画的结束 **********************************************************************************/

    public void endAnimation(RecyclerView.ViewHolder item) {
        // 获取对应的viewHolder，并取消动画
        View view = item.itemView;
        view.animate().cancel();

        int i;
        for(i = this.mPendingMoves.size() - 1; i >= 0; --i) {
            BaseItemAnimator.MoveInfo moveInfo = this.mPendingMoves.get(i);
            if (moveInfo.holder == item) {
                view.setTranslationY(0.0F);
                view.setTranslationX(0.0F);
                this.dispatchMoveFinished(item);
                this.mPendingMoves.remove(i);
            }
        }

        this.endChangeAnimation(this.mPendingChanges, item);
        if (this.mPendingRemovals.remove(item)) {
            view.setAlpha(1.0F);
            this.dispatchRemoveFinished(item);
        }

        if (this.mPendingAdditions.remove(item)) {
            view.setAlpha(1.0F);
            this.dispatchAddFinished(item);
        }

        ArrayList moves;
        for(i = this.mChangesList.size() - 1; i >= 0; --i) {
            moves = this.mChangesList.get(i);
            this.endChangeAnimation(moves, item);
            if (moves.isEmpty()) {
                this.mChangesList.remove(i);
            }
        }

        for(i = this.mMovesList.size() - 1; i >= 0; --i) {
            moves = this.mMovesList.get(i);

            for(int j = moves.size() - 1; j >= 0; --j) {
                BaseItemAnimator.MoveInfo moveInfo = (BaseItemAnimator.MoveInfo)moves.get(j);
                if (moveInfo.holder == item) {
                    view.setTranslationY(0.0F);
                    view.setTranslationX(0.0F);
                    this.dispatchMoveFinished(item);
                    moves.remove(j);
                    if (moves.isEmpty()) {
                        this.mMovesList.remove(i);
                    }
                    break;
                }
            }
        }

        for(i = this.mAdditionsList.size() - 1; i >= 0; --i) {
            moves = this.mAdditionsList.get(i);
            if (moves.remove(item)) {
                view.setAlpha(1.0F);
                this.dispatchAddFinished(item);
                if (moves.isEmpty()) {
                    this.mAdditionsList.remove(i);
                }
            }
        }

        if (this.mRemoveAnimations.remove(item)) {
            ;
        }

        if (this.mAddAnimations.remove(item)) {
            ;
        }

        if (this.mChangeAnimations.remove(item)) {
            ;
        }

        if (this.mMoveAnimations.remove(item)) {
            ;
        }

        this.dispatchFinishedWhenDone();
    }

    public void endAnimations() {
        int count = this.mPendingMoves.size();

        int listCount;
        for(listCount = count - 1; listCount >= 0; --listCount) {
            BaseItemAnimator.MoveInfo item = this.mPendingMoves.get(listCount);
            View view = item.holder.itemView;
            view.setTranslationY(0.0F);
            view.setTranslationX(0.0F);
            this.dispatchMoveFinished(item.holder);
            this.mPendingMoves.remove(listCount);
        }

        count = this.mPendingRemovals.size();

        RecyclerView.ViewHolder item;
        for(listCount = count - 1; listCount >= 0; --listCount) {
            item = this.mPendingRemovals.get(listCount);
            this.dispatchRemoveFinished(item);
            this.mPendingRemovals.remove(listCount);
        }

        count = this.mPendingAdditions.size();

        for(listCount = count - 1; listCount >= 0; --listCount) {
            item = this.mPendingAdditions.get(listCount);
            item.itemView.setAlpha(1.0F);
            this.dispatchAddFinished(item);
            this.mPendingAdditions.remove(listCount);
        }

        count = this.mPendingChanges.size();

        for(listCount = count - 1; listCount >= 0; --listCount) {
            this.endChangeAnimationIfNecessary(this.mPendingChanges.get(listCount));
        }

        this.mPendingChanges.clear();
        if (this.isRunning()) {
            listCount = this.mMovesList.size();

            int j;
            int i;
            ArrayList changes;
            for(i = listCount - 1; i >= 0; --i) {
                changes = this.mMovesList.get(i);
                count = changes.size();

                for(j = count - 1; j >= 0; --j) {
                    BaseItemAnimator.MoveInfo moveInfo = (BaseItemAnimator.MoveInfo)changes.get(j);
                    RecyclerView.ViewHolder item1 = moveInfo.holder;
                    View view = item1.itemView;
                    view.setTranslationY(0.0F);
                    view.setTranslationX(0.0F);
                    this.dispatchMoveFinished(moveInfo.holder);
                    changes.remove(j);
                    if (changes.isEmpty()) {
                        this.mMovesList.remove(changes);
                    }
                }
            }

            listCount = this.mAdditionsList.size();

            for(i = listCount - 1; i >= 0; --i) {
                changes = this.mAdditionsList.get(i);
                count = changes.size();

                for(j = count - 1; j >= 0; --j) {
                    RecyclerView.ViewHolder item2 = (RecyclerView.ViewHolder)changes.get(j);
                    View view = item2.itemView;
                    view.setAlpha(1.0F);
                    this.dispatchAddFinished(item2);
                    changes.remove(j);
                    if (changes.isEmpty()) {
                        this.mAdditionsList.remove(changes);
                    }
                }
            }

            listCount = this.mChangesList.size();

            for(i = listCount - 1; i >= 0; --i) {
                changes = this.mChangesList.get(i);
                count = changes.size();

                for(j = count - 1; j >= 0; --j) {
                    this.endChangeAnimationIfNecessary((BaseItemAnimator.ChangeInfo)changes.get(j));
                    if (changes.isEmpty()) {
                        this.mChangesList.remove(changes);
                    }
                }
            }

            this.cancelAll(this.mRemoveAnimations);
            this.cancelAll(this.mMoveAnimations);
            this.cancelAll(this.mAddAnimations);
            this.cancelAll(this.mChangeAnimations);
            this.dispatchAnimationsFinished();
        }
    }

    private void endChangeAnimation(List<BaseItemAnimator.ChangeInfo> infoList, RecyclerView.ViewHolder item) {
        for(int i = infoList.size() - 1; i >= 0; --i) {
            BaseItemAnimator.ChangeInfo changeInfo = infoList.get(i);
            if (this.endChangeAnimationIfNecessary(changeInfo, item) && changeInfo.oldHolder == null && changeInfo.newHolder == null) {
                infoList.remove(changeInfo);
            }
        }

    }

    private void endChangeAnimationIfNecessary(BaseItemAnimator.ChangeInfo changeInfo) {
        if (changeInfo.oldHolder != null) {
            this.endChangeAnimationIfNecessary(changeInfo, changeInfo.oldHolder);
        }

        if (changeInfo.newHolder != null) {
            this.endChangeAnimationIfNecessary(changeInfo, changeInfo.newHolder);
        }

    }

    private boolean endChangeAnimationIfNecessary(BaseItemAnimator.ChangeInfo changeInfo, RecyclerView.ViewHolder item) {
        boolean oldItem = false;
        if (changeInfo.newHolder == item) {
            changeInfo.newHolder = null;
        } else {
            if (changeInfo.oldHolder != item) {
                return false;
            }

            changeInfo.oldHolder = null;
            oldItem = true;
        }

        item.itemView.setAlpha(1.0F);
        item.itemView.setTranslationX(0.0F);
        item.itemView.setTranslationY(0.0F);
        this.dispatchChangeFinished(item, oldItem);
        return true;
    }

    // 重置动画
    private void resetAnimation(RecyclerView.ViewHolder holder) {
        // 判断默认插值器是否为空，如果为空设置插值器为加减速插值器
        if (sDefaultInterpolator == null) {
            sDefaultInterpolator = (new ValueAnimator()).getInterpolator();
        }
        // 设置默认的插值器
        holder.itemView.animate().setInterpolator(sDefaultInterpolator);
        // 结束对应holder的动画
        this.endAnimation(holder);
    }

    // 判断item的动画是否在进行
    public boolean isRunning() {
        return !this.mPendingAdditions.isEmpty() ||
                !this.mPendingChanges.isEmpty() ||
                !this.mPendingMoves.isEmpty() ||
                !this.mPendingRemovals.isEmpty() ||
                !this.mMoveAnimations.isEmpty() ||
                !this.mRemoveAnimations.isEmpty() ||
                !this.mAddAnimations.isEmpty() ||
                !this.mChangeAnimations.isEmpty() ||
                !this.mMovesList.isEmpty() ||
                !this.mAdditionsList.isEmpty() ||
                !this.mChangesList.isEmpty();
    }

    // 完全结束移除动画后的操作
    void dispatchFinishedWhenDone() {
        if (!this.isRunning()) {
            // 如果动画没有在运行了，就使动画全部结束
            this.dispatchAnimationsFinished();
        }

    }

    // 取消所有itemView动画
    void cancelAll(List<RecyclerView.ViewHolder> viewHolders) {
        for(int i = viewHolders.size() - 1; i >= 0; --i) {
            (viewHolders.get(i)).itemView.animate().cancel();
        }

    }

    public boolean canReuseUpdatedViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull List<Object> payloads) {
        return !payloads.isEmpty() || super.canReuseUpdatedViewHolder(viewHolder, payloads);
    }

    /** 抽象方法，用于给继承类实现具体的动画效果 **********************************************************/

    protected abstract void addAnimationInit(final RecyclerView.ViewHolder holder);

    protected abstract ViewPropertyAnimator addAnimation(ViewPropertyAnimator animation);

    protected abstract void animationEnd(final View view);

    protected abstract ViewPropertyAnimator removeAnimation(final ViewPropertyAnimator animation);

    /** setter&&getter ****************************************************************************/

    public long getAddAnimDelay() {
        return mAddAnimDelay;
    }

    public void setAddAnimDelay(long mAddAnimDelay) {
        this.mAddAnimDelay = mAddAnimDelay;
    }

    public TimeInterpolator getAddAnimInterpolator() {
        return mAddAnimInterpolator;
    }

    public void setAddAnimInterpolator(TimeInterpolator mAddAnimInterpolator) {
        this.mAddAnimInterpolator = mAddAnimInterpolator;
    }

    public AnimatorListenerAdapter getAddAnimListener() {
        return mAddAnimListener;
    }

    public void setAddAnimListener(AnimatorListenerAdapter mAddAnimListener) {
        this.mAddAnimListener = mAddAnimListener;
    }

    public long getRemoveAnimDelay() {
        return mRemoveAnimDelay;
    }

    public void setRemoveAnimDelay(long mRemoveAnimDelay) {
        this.mRemoveAnimDelay = mRemoveAnimDelay;
    }

    public TimeInterpolator getRemoveAnimInterpolator() {
        return mRemoveAnimInterpolator;
    }

    public void setRemoveAnimInterpolator(TimeInterpolator mRemoveAnimInterpolator) {
        this.mRemoveAnimInterpolator = mRemoveAnimInterpolator;
    }

    public AnimatorListenerAdapter getRemoveAnimListener() {
        return mRemoveAnimListener;
    }

    public void setRemoveAnimListener(AnimatorListenerAdapter mRemoveAnimListener) {
        this.mRemoveAnimListener = mRemoveAnimListener;
    }
}
