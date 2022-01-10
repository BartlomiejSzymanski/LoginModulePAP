package loginModule;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

import io.nayuki.qrcodegen.QrCode;

public class QRGenerator {

	 // Creates a single QR Code, then writes it to a PNG file and an SVG file.
	 	public static void generateQr(String input) throws IOException {
	 		
	 		QrCode.Ecc errCorLvl = QrCode.Ecc.LOW;  // Error correction level

	 		QrCode qr = QrCode.encodeText(input, errCorLvl);  // Make the QR Code symbol
	 		
	 		BufferedImage img = toImage(qr, 10, 4);          // Convert to bitmap image
	 		File imgFile = new File("USER_QR_CODE.png");   // File path for output
	 		ImageIO.write(img, "png", imgFile);              // Write image to file
	 		
	 	}
	 	

	 	private static BufferedImage toImage(QrCode qr, int scale, int border) {
			return toImage(qr, scale, border, 0xFFFFFF, 0x000000);
		}
	 	
	 	
	 	private static BufferedImage toImage(QrCode qr, int scale, int border, int lightColor, int darkColor) {
			Objects.requireNonNull(qr);
			if (scale <= 0 || border < 0)
				throw new IllegalArgumentException("Value out of range");
			if (border > Integer.MAX_VALUE / 2 || qr.size + border * 2L > Integer.MAX_VALUE / scale)
				throw new IllegalArgumentException("Scale or border too large");
			
			BufferedImage result = new BufferedImage((qr.size + border * 2) * scale, (qr.size + border * 2) * scale, BufferedImage.TYPE_INT_RGB);
			for (int y = 0; y < result.getHeight(); y++) {
				for (int x = 0; x < result.getWidth(); x++) {
					boolean color = qr.getModule(x / scale - border, y / scale - border);
					result.setRGB(x, y, color ? darkColor : lightColor);
				}
			}
			return result;
		}
	 	
	 	
	 	
	 	
}
