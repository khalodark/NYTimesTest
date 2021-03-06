package com.mytask.nytimespopular;


import android.content.Context;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.mytask.nytimespopular.base.BaseActions;
import com.mytask.nytimespopular.repository.DataManager;
import com.mytask.nytimespopular.ui.details.DetailsFragmentViewModel;
import com.mytask.nytimespopular.ui.main.MainActivityViewModel;
import com.mytask.nytimespopular.ui.master.MasterFragmentViewModel;

/**
 * @ViewDataBinding : Base class for generated data binding classes
 * @ViewModel: The purpose of the ViewModel is to acquire and keep the information that is necessary for an
 * Activity or a Fragment. The Activity or the Fragment should be able to observe changes in the
 * ViewModel. ViewModels usually expose this information via LiveData or Android Data
 * Binding. You can also use any observability construct from you favorite framework.
 * <p>
 * ViewModel's only responsibility is to manage the data for the UI. It <b>should never</b> access
 * your view hierarchy or hold a reference back to the Activity or the Fragment.
 * <p>
 * ViewModelProviderFactory - Simple factory, which calls empty constructor on the give class.
 */
public class ViewModelProviderFactory<V extends ViewDataBinding, N extends BaseActions>
        extends ViewModelProvider.NewInstanceFactory {

    private final DataManager dataManager;
    private final Context mContext;


    public ViewModelProviderFactory(DataManager dataManager, Context mContext) {
        this.dataManager = dataManager;
        this.mContext = mContext;
    }

    public <T extends ViewModel> T create(Class<T> modelClass, V viewDataBinding, N navigation) {
        if (modelClass.isAssignableFrom(MainActivityViewModel.class))
            return (T) new MainActivityViewModel(mContext, dataManager, viewDataBinding, navigation);
        else if (modelClass.isAssignableFrom(MasterFragmentViewModel.class))
            return (T) new MasterFragmentViewModel(mContext, dataManager, viewDataBinding, navigation);
        else if (modelClass.isAssignableFrom(DetailsFragmentViewModel.class))
            return (T) new DetailsFragmentViewModel(mContext, dataManager, viewDataBinding, navigation);
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}