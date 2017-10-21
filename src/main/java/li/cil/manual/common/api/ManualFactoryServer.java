package li.cil.manual.common.api;

import li.cil.manual.api.detail.ManualDefinition;
import li.cil.manual.api.detail.ManualFactory;
import li.cil.manual.common.item.ItemBookManual;
import net.minecraft.item.Item;

import javax.annotation.Nullable;

public class ManualFactoryServer implements ManualFactory {
    @Nullable
    @Override
    public ManualDefinition createManual() {
        return null;
    }

    @Override
    public Item createItemForManual(@Nullable final ManualDefinition manual) {
        if (manual != null){
            throw new IllegalArgumentException("manual should be null on server side!");
        }
        return createItemForManualReal(manual);
    }

    protected ItemBookManual createItemForManualReal(@Nullable final ManualDefinition manual){
        return new ItemBookManual(manual);
    }
}
