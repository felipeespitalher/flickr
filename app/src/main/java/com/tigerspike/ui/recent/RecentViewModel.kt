package com.tigerspike.ui.recent

import com.tigerspike.data.repository.PhotoRepostory
import com.tigerspike.ui.commons.BaseViewModel
import javax.inject.Inject

class RecentViewModel @Inject constructor(
    private val photoRepostory: PhotoRepostory
) : BaseViewModel() {



}