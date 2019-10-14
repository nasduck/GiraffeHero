package com.zoopark.rv.base;

import android.util.SparseArray;

import com.zoopark.rv.base.BaseItemProvider;

public class ProviderDelegate {

    private SparseArray<BaseItemProvider> mProviderList = new SparseArray<>();

    public void registerProvider(BaseItemProvider provider) {
        if (provider == null) {
            throw new RuntimeException("ItemProvider can not be null");
        }

        int key = mProviderList.size();

        if (mProviderList.get(key) == null) {
            mProviderList.put(key, provider);
        }
    }

    public void registerProviders(BaseItemProvider ... providerList) {
        for (BaseItemProvider provider : providerList) {
            registerProvider(provider);
        }
    }

    public SparseArray<BaseItemProvider> getProviderList(){
        return mProviderList;
    }

    public void clearProviderList() {
        mProviderList.clear();
        mProviderList = new SparseArray<>();
    }

    public BaseItemProvider getProvider(int section) {
        return mProviderList.get(section);
    }

}