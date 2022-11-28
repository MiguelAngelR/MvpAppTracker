package com.mike.core.common

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mike.core.R
import com.mike.core.databinding.DialogContentBinding
import com.mike.core.util.gone
import com.mike.core.util.setSafeOnClickListener
import com.mike.core.util.visible

open class AppTrackerDialogBuilder(private val context: Context) {


    enum class DialogType { WARNING, ERROR, ASK, LOADING, INFO, NO_ICON, SUCCESS }

    private val materialAlertDialogBuilder = MaterialAlertDialogBuilder(context)
    private var alertDialog: AlertDialog? = null

    private val contentBinding: DialogContentBinding by lazy {
        DialogContentBinding.inflate(LayoutInflater.from(context))
    }

    private var dialogType: DialogType = DialogType.NO_ICON

    init {
        materialAlertDialogBuilder.setView(contentBinding.root)
    }

    fun setIcon(icon: Drawable?): AppTrackerDialogBuilder {
        contentBinding.ivDialogIcon.run {
            setImageDrawable(icon)
            isVisible = icon != null
        }
        return this
    }

    fun setIcon(@DrawableRes iconId: Int): AppTrackerDialogBuilder {
        contentBinding.ivDialogIcon.setImageResource(iconId)
        return this
    }

    fun setTitle(title: CharSequence?): AppTrackerDialogBuilder {
        contentBinding.tvDialogTitle.run {
            text = title
            isVisible = title != null
        }
        return this
    }

    fun setTitle(@StringRes titleId: Int): AppTrackerDialogBuilder {
        contentBinding.tvDialogTitle.setText(titleId)
        return this
    }

    fun setMessage(@StringRes messageId: Int): AppTrackerDialogBuilder {
        contentBinding.tvDialogMessage.setText(messageId)
        return this
    }

    fun setMessage(message: CharSequence?): AppTrackerDialogBuilder {
        contentBinding.tvDialogMessage.run {
            text = message
            isVisible = message != null
        }
        return this
    }

    fun setDialogType(dialogType: DialogType): AppTrackerDialogBuilder {
        this.dialogType = dialogType
        val icon: Int? = when (dialogType) {
            DialogType.WARNING -> R.drawable.ic_circle_exclamation
            DialogType.ERROR -> R.drawable.ic_circle_error
            DialogType.ASK -> R.drawable.ic_circle_question
            DialogType.INFO -> R.drawable.ic_circle_info
            DialogType.LOADING -> R.drawable.ic_circle_in_process
            DialogType.SUCCESS -> R.drawable.ic_alert
            DialogType.NO_ICON -> null
        }
        if (icon != null) {
            contentBinding.ivDialogIcon.run {
                setImageResource(icon)
                visible()
            }
        } else {
            contentBinding.ivDialogIcon.run {
                setImageDrawable(null)
                gone()
            }
        }
        return this
    }

    fun setPositiveButtonVisible(isVisible: Boolean): AppTrackerDialogBuilder {
        contentBinding.btnPositiveAction.isVisible = isVisible
        return this
    }

    fun setNegativeButtonVisible(isVisible: Boolean): AppTrackerDialogBuilder {
        contentBinding.btnNegativeAction.isVisible = isVisible
        return this
    }

    fun setPositiveButton(
        text: CharSequence?,
        callback: ((AlertDialog?) -> Unit)? = null,
    ): AppTrackerDialogBuilder {
        contentBinding.btnPositiveAction.run {
            this.text = text
            setSafeOnClickListener { callback?.invoke(alertDialog) }
            visible()
        }
        return this
    }

    fun setPositiveButton(
        @StringRes textId: Int,
        callback: ((AlertDialog?) -> Unit)? = null,
    ): AppTrackerDialogBuilder {
        contentBinding.btnPositiveAction.run {
            this.setText(textId)
            setSafeOnClickListener { callback?.invoke(alertDialog) }
            visible()
        }
        return this
    }

    fun setPositiveButton(callback: ((AlertDialog?) -> Unit)? = null): AppTrackerDialogBuilder {
        contentBinding.btnPositiveAction.run {
            this.setText(R.string.acept)
            setSafeOnClickListener { callback?.invoke(alertDialog) }
            visible()
        }
        return this
    }

    fun setPositiveButtonIcon(icon: Drawable?): AppTrackerDialogBuilder {
        contentBinding.btnPositiveAction.icon = icon
        return this
    }

    fun setNegativeButton(
        text: CharSequence?,
        callback: ((AlertDialog?) -> Unit)? = null,
    ): AppTrackerDialogBuilder {
        contentBinding.btnNegativeAction.run {
            this.text = text
            setSafeOnClickListener { callback?.invoke(alertDialog) }
            visible()
        }
        return this
    }

    fun setNegativeButton(
        @StringRes textId: Int,
        callback: ((AlertDialog?) -> Unit)? = null,
    ): AppTrackerDialogBuilder {
        contentBinding.btnNegativeAction.run {
            this.setText(textId)
            setSafeOnClickListener { callback?.invoke(alertDialog) }
            visible()
        }
        return this
    }

    fun setNegativeButton(callback: ((AlertDialog?) -> Unit)? = null): AppTrackerDialogBuilder {
        contentBinding.btnNegativeAction.run {
            this.setText(R.string.cancel)
            setSafeOnClickListener { callback?.invoke(alertDialog) }
            visible()
        }
        return this
    }

    fun setNegativeButtonIcon(icon: Drawable?): AppTrackerDialogBuilder {
        contentBinding.btnNegativeAction.icon = icon
        return this
    }

    fun setPositiveButtonTint(@ColorInt color: Int): AppTrackerDialogBuilder {
        contentBinding.btnPositiveAction.backgroundTintList = ColorStateList.valueOf(color)
        return this
    }

    fun setNegativeButtonTint(@ColorInt color: Int): AppTrackerDialogBuilder {
        contentBinding.btnNegativeAction.backgroundTintList = ColorStateList.valueOf(color)
        return this
    }

    fun setCancelable(isCancelable: Boolean): AppTrackerDialogBuilder {
        materialAlertDialogBuilder.setCancelable(isCancelable)
        return this
    }

    fun create(): AlertDialog = materialAlertDialogBuilder.create().also {
        alertDialog = it
    }

    fun show() {
        if (alertDialog == null) {
            alertDialog = this.create()
        }
        alertDialog!!.show()
    }

    fun dismiss() = alertDialog?.dismiss()

    fun hide() = alertDialog?.hide()

}