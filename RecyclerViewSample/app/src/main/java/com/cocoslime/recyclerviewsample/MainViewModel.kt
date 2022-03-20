package com.cocoslime.recyclerviewsample

import android.app.Application
import androidx.lifecycle.*

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val _commitList = MutableLiveData<List<GitCommit>>(mutableListOf())
    val commitList : LiveData<List<GitCommit>>
        get() = _commitList

    init {
        setDummyCommitList()
    }

    private fun setDummyCommitList() {
        // https://github.com/android/architecture-components-samples/commits/main
         val dummyList = listOf<GitCommit>(
             GitCommit("Merge pull request #1049 from android/paging-test-coroutine-1.6", "Dustin Lam", "Sun Feb 27 22:52:22 2022 -0700", "0905d0e", "Update samples to kotlinx.coroutines.test 1.6"),
             GitCommit("Update Kotlin and coroutine test versions to 1.6", "Márton Braun", "Fri Feb 25 12:59:41 2022 +0100", "c4dfa15"),
             GitCommit("Update Paging sample test to ", "Márton Braun", "Mon Feb 21 16:37:24 2022 +0100", "20de5fab6e646501bd034684a1c434710e1177e8"),
             GitCommit("Merge pull request #1030 from dlam/dlam/update-paging", "Dustin Lam", "Wed Oct 27 11:24:03 2021 -0700", "79e64a", "Cleanup + bump paging to 3.1.0-beta01"),
             GitCommit("Cleanup + bump paging to 3.1.0-beta01", "Dustin Lam", "Wed Oct 20 21:14:41 2021 -0700", "cb6140f43d82e611498d755423c2d915a0c7ee7f"),
             GitCommit("Update WorkManager sample to 2.7-rc (#1025)", "Caren", "Mon Oct 11 15:12:43 2021 -0700", "cb6140f43d82e611498d755423c2d915a0c7ee7f",
                 "* WorkManager library updated to 2.7.0-alpha01\n" +
                     "* Upgrade workmanager to 2.7-beta01 (#1017)\n" +
                     "* Upgrade workmanager to 2.7-beta01\n" +
                     "* Remove extra equal sign\n" +
                     "* Fix rebase errors\n" +
                     "* Update SaveImageToGalleryWorker to be CoroutineWorker\n" +
                     "* Add NotificationUtils class\n" +
                     "* Update WorkManager version to 2.7.0-rc01\n" +
                     "* Move notification ID to companion object and use 31 as compile_sdk version\n" +
                     "* Remove wildcard imports and implement more descriptive variable names in NotificationUtils\n" +
                     "* Make notificationImportance an argument with default value when creating notification channel\n" +
                     "Co-authored-by: Murat Yener <yenerm@google.com>"
             ),
            GitCommit("Merge pull request #1024 from dlam/dlam/update-paging", "Dustin Lam", "Wed Oct 6 10:38:03 2021 -0700", "1c8fc23bdbcc096cae6046c3a819725e04f74f53", "Bump room to 2.4.0-alpha05"),
            GitCommit("Bump room to 2.4.0-alpha05", "Dustin Lam", "Tue Oct 5 16:37:11 2021 -0700", "fcfa237161aa99912355beffa5602c1be05ee399")
         )
        _commitList.value = dummyList
    }

    fun sortWithTime() {
        _commitList.value = _commitList.value?.sortedBy { it.timeStamp.toCommitDate().time }
    }

    fun sortWithAuthor() {
        _commitList.value = _commitList.value?.sortedBy { it.author }
    }

    /**
     * Factory for constructing DevByteViewModel with parameter
     */
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}