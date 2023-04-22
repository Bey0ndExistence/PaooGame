package Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {
    private static LoadSave instance;

    public static final String PLAYER_ATLAS = "V_SpriteSheetFinal2.0.png";
    public static final String LEVEL1_TILESET1 = "Gata.png";
    public static final String LEVEL1_TILESET2 = "yessir.png";
    public static final String BRIDGE_TILE = "bridgeFinal.png";
    public static final String BG_FAR = "Untitled-1.png";
    public static final String BG_MIDDLE = "Untitled-2.png";
    public static final String BG_CLOSE = "Untitled-3.png";
    public static final String BANNER_START = "banner-final.png";
    public static final String BANNER_R = "banner-R.png";
    public static final String BANNER_FLASH = "banner-flash.png";
    public static final String BANNER_LIFE = "banner-Life.png";
    public static final String BANNER_HOTEL = "banner-hotel.png";
    public static final String BANNER_JAPONEZA_VERDE = "banner-japoneza-verde.png";
    public static final String BANNER_COKE = "banner-coke.png";
    public static final String BANNER_JAPONEZA_MOV = "banner-japoneza-mov.png";
    public static final String BANNER_SUSHI = "banner-sushi.png";
    public static final String GUN_RED = "gunRed.png";


    private LoadSave() {
        // private constructor to prevent instantiation from outside
    }

    public static LoadSave getInstance() {
        if (instance == null) {
            instance = new LoadSave();
        }
        return instance;
    }

    public BufferedImage getAtlas(String fileName) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }
}
