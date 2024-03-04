package xaeroplus.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xaero.common.patreon.Patreon;
import xaeroplus.settings.XaeroPlusSettingRegistry;

@Mixin(value = Patreon.class, remap = false)
public class MixinPatreon {

    @Inject(method = "checkPatreon(Lxaero/common/AXaeroMinimap;)V", at = @At("HEAD"), cancellable = true)
    private static void disableInternetAccessCheck(final CallbackInfo ci) {
        if (XaeroPlusSettingRegistry.disableXaeroInternetAccess.getValue()) ci.cancel();
    }
}
