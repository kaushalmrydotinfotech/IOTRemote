package com.rydot.iotRemote.customSwitch

interface OnToggledListener {
    /**
     * Called when a view changes it's state.
     *
     * @param toggleableView The view which either is on/off.
     * @param isOn The on/off state of switch, true when switch turns on.
     */
    fun onSwitched(toggleableView: ToggleableView?, isOn: Boolean)
}
