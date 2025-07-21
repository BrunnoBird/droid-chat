package com.example.droidchat.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Badge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.droidchat.R
import com.example.droidchat.ui.theme.DroidChatTheme

@Composable
fun ChatItem(modifier: Modifier = Modifier) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        val (
            avatarRef,
            firstNameRef,
            lasMessageRef,
            spaceFirstNameAndLastMessageRef,
            lastMessageTimeRef,
            spaceTimeAndUnreadRef,
            unreadCountRef
        ) = createRefs()

        createVerticalChain(
            firstNameRef,
            spaceFirstNameAndLastMessageRef,
            lasMessageRef,
            chainStyle = ChainStyle.Packed
        )

        Image(
            painter = painterResource(id = R.drawable.no_profile_image),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(60.dp)
                .constrainAs(avatarRef) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top, margin = 16.dp)
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                }
        )

        Text(
            "Jazz ❤️",
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.constrainAs(firstNameRef) {
                top.linkTo(avatarRef.top)
                bottom.linkTo(spaceFirstNameAndLastMessageRef.top)
                start.linkTo(avatarRef.end, margin = 10.dp)
            }
        )

        Spacer(
            modifier = Modifier
                .height(4.dp)
                .constrainAs(spaceFirstNameAndLastMessageRef) {
                    top.linkTo(firstNameRef.bottom)
                    start.linkTo(firstNameRef.start)
                    bottom.linkTo(lasMessageRef.top)
                }
        )

        Text(
            text = "Ok, vamos!",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.constrainAs(lasMessageRef) {
                top.linkTo(spaceFirstNameAndLastMessageRef.bottom)
                start.linkTo(firstNameRef.start)
                bottom.linkTo(avatarRef.bottom)
            }
        )

        Text(
            text = "12:00",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.constrainAs(lastMessageTimeRef) {
                top.linkTo(firstNameRef.top)
                bottom.linkTo(spaceFirstNameAndLastMessageRef.top)
                end.linkTo(parent.end)
            }
        )

        Spacer(
            modifier = Modifier
                .height(4.dp)
                .constrainAs(spaceTimeAndUnreadRef) {
                    top.linkTo(lastMessageTimeRef.bottom)
                    end.linkTo(parent.end)
                    bottom.linkTo(lasMessageRef.top)
                }
        )

        Badge(
            containerColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier.constrainAs(unreadCountRef) {
                top.linkTo(spaceTimeAndUnreadRef.bottom)
                end.linkTo(parent.end)
                bottom.linkTo(avatarRef.bottom)
            }
        ) {
            Text(
                text = "4",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview
@Composable
private fun ChatItemPreview() {
    DroidChatTheme {
        ChatItem()
    }
}