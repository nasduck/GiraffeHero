package com.zoopark.rv.base;

import android.util.SparseArray;

import com.zoopark.rv.animation.enter.BaseInAnimation;

public class ProviderDelegate {

    private SparseArray<BaseProvider> mProviderList = new SparseArray<>();
    private SparseArray<BaseItemProvider> mSectionList = new SparseArray<>();

    private int mAnimationCount = 0;
    private BaseInAnimation mAnimation;

    private void registerProvider(BaseProvider provider) {
        if (provider == null) {
            throw new RuntimeException("ItemProvider can not be null");
        }

        int key = mProviderList.size();

        if (mProviderList.get(key) == null) {
            mProviderList.put(key, provider);
        }
    }

    private void registerSectionProvider(BaseItemProvider provider) {
        if (provider == null) {
            throw new RuntimeException("SectionProvider can not be null");
        }

        int key = mSectionList.size();

        if (mSectionList.get(key) == null) {
            mSectionList.put(key, provider);
        }
    }

    private void registerProviderAnimation(BaseItemProvider provider) {
        if (provider.getAnimator() != null) {
            mAnimationCount++;
            mAnimation = provider.getAnimator();
        }
    }

    public void registerProviders(BaseItemProvider ... providerList) {
        for (BaseItemProvider provider : providerList) {
            if (provider.hasHeader()) registerProvider(provider.getHeader());
            registerProvider(provider);
            if (provider.hasFooter()) registerProvider(provider.getFooter());
            registerSectionProvider(provider);
            registerProviderAnimation(provider);
        }
    }

    public SparseArray<BaseProvider> getProviderList() {
        return mProviderList;
    }

    public SparseArray<BaseItemProvider> getSectionList() {
        return mSectionList;
    }

    public int indexOfProvider(BaseProvider provider) {
        return mProviderList.indexOfValue(provider);
    }

    public void clearProviderList() {
        mProviderList.clear();
        mProviderList = new SparseArray<>();
    }

    public void clearSectionList() {
        mSectionList.clear();
        mSectionList = new SparseArray<>();
    }

    public BaseProvider getProvider(int providerIndex) {
        return mProviderList.get(providerIndex);
    }

    public BaseItemProvider getSection(int section) {
        return mSectionList.get(section);
    }

    public int getAnimationCount() {
        return mAnimationCount;
    }

    public BaseInAnimation getAnimation() {
        return mAnimation;
    }
}