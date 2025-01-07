package com.mmag.stephenking.ui.commonComponents

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mmag.stephenking.R


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun BookListListScreenTopAppBar(
    title: String = stringResource(id = R.string.app_name),
) {
    CenterAlignedTopAppBar(title = {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 32.dp, bottom = 16.dp)
        )
    })
}