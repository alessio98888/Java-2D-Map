import java.awt.*;

public class ColorUtilities {
    public static Color transitionOfHueRange(double percentage, int startHue, int endHue) {
        // From 'startHue' 'percentage'-many to 'endHue'
        // Finally map from [0°, 360°] -> [0, 1.0] by dividing
        double hue = ((percentage * (endHue - startHue)) + startHue) / 360;

        double saturation = 1.0;
        double lightness = 0.5;

        // Get the color
        return hslColorToRgb(hue, saturation, lightness);
    }
    public static Color hslColorToRgb(double hue, double saturation, double lightness) {
        if (saturation == 0.0) {
            // The color is achromatic (has no color)
            // Thus use its lightness for a grey-scale color
            int grey = percToColor(lightness);
            return new Color(grey, grey, grey);
        }

        double q;
        if (lightness < 0.5) {
            q = lightness * (1 + saturation);
        } else {
            q = lightness + saturation - lightness * saturation;
        }
        double p = 2 * lightness - q;

        double oneThird = 1.0 / 3;
        int red = percToColor(hueToRgb(p, q, hue + oneThird));
        int green = percToColor(hueToRgb(p, q, hue));
        int blue = percToColor(hueToRgb(p, q, hue - oneThird));

        return new Color(red, green, blue);
    }
    public static double hueToRgb(double p, double q, double t) {
        if (t < 0) {
            t += 1;
        }
        if (t > 1) {
            t -= 1;
        }

        if (t < 1.0 / 6) {
            return p + (q - p) * 6 * t;
        }
        if (t < 1.0 / 2) {
            return q;
        }
        if (t < 2.0 / 3) {
            return p + (q - p) * (2.0 / 3 - t) * 6;
        }
        return p;
    }

    public static int percToColor(double percentage) {
        return (int) Math.round(percentage * 255);
    }
}
