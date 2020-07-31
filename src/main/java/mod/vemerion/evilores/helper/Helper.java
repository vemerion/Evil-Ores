package mod.vemerion.evilores.helper;

import net.minecraft.util.math.MathHelper;

public class Helper {
    public static float pingpong(float start, float stop, float value) {
		value = value % 2f;
		if (value > 1)
			value = 2 - value;
		return MathHelper.lerp(value, start, stop);
	}
}
