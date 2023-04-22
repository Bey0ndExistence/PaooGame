package levels;

import Entities.Entity;
import Entities.Guns;
import Entities.TilesHitBox;
import Entities.TilesHitBoxAnimated;
import Game.Game;
import Utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import static Utils.Constants.PlayerConstants.*;
public class LevelManager {

    private Game game;
    private BufferedImage BG_FAR,BG_MIDDLE, BG_CLOSE,bridge,banner_start,banner_R,banner_flash, banner_life ,longBlock, smallBlock,banner_Hotel, banner_Japoneza_verde,banner_coke,banner_Japoneza_mov,banner_sushi;
    private Camera camera;
    private BackgroundLayer[] backgroundLayers;

    private BufferedImage[] bannerStart,bannerR,bannerFlash,bannerLife,bannerJaponezaverde,bannerCoke, bannerJaponezamov,bannerSushi;

    private BufferedImage secondGun;
    private TilesHitBox[]  hitBoxes;


    LoadSave singleton;
    public LevelManager(Game game){
        this.game =game;
        camera = new Camera(150, 50, 1920, 768, game.getPlayer());
        importTileSprites();
        LoadBannerStart();
        LoadBannerR();
        LoadBannerFlash();
        LoadBannerLife();
        LoadBannerJaponezaVerde();
        LoadBannerCoke();
        LoadBannerJaponezaMov();
        LoadBannerSushi();
        LoadTileHitboxes();

    }

    public TilesHitBox[] getHitBoxes(){
       return hitBoxes;

    }
    private void importTileSprites() {
        singleton = LoadSave.getInstance();

        //incarcare imagini
        BufferedImage bridgeTemp = singleton.getAtlas(singleton.BRIDGE_TILE);
        BufferedImage tileset1 = singleton.getAtlas(singleton.LEVEL1_TILESET1);
        BufferedImage tileset2 = singleton.getAtlas(singleton.LEVEL1_TILESET2);

        //Background layers
        BG_FAR = singleton.getAtlas(singleton.BG_FAR);
        BG_MIDDLE = singleton.getAtlas(singleton.BG_MIDDLE);
        BG_CLOSE = singleton.getAtlas(singleton.BG_CLOSE);

        //BANNER
        banner_Hotel = singleton.getAtlas(singleton.BANNER_HOTEL);

        //TILES
        bridge = bridgeTemp.getSubimage(0,96,1670,288); // platforma 1
        smallBlock = tileset1.getSubimage(0,42,298,288); // platforma 3
        longBlock = tileset2.getSubimage(104,58,920,288); // platforma 2

        //GUNS

        secondGun = singleton.getAtlas(LoadSave.GUN_RED);
        
    }

    public void LoadTileHitboxes(){
        backgroundLayers = new BackgroundLayer[] {
                new BackgroundLayer(BG_FAR, 0.5,0,0),
                new BackgroundLayer(BG_MIDDLE, 0.7,0,0),
                new BackgroundLayer(BG_CLOSE, 0.9,0,0),
        };
        this.hitBoxes = new TilesHitBox[] { new TilesHitBox(500,480,1670,288, bridge),
                                            new TilesHitBoxAnimated(1000,300,270,180,bannerStart,BANNER_START),
                                            new TilesHitBox(2300,530,920,288, longBlock),
                                            new TilesHitBoxAnimated(2600,580,162,195,bannerR,BANNER_R),
                                            new TilesHitBoxAnimated(1065+100,610,111,180,bannerLife,BANNER_LIFE),
                                            new TilesHitBoxAnimated(1800+100,280,165,204,bannerFlash,BANNER_FLASH),
                                            new TilesHitBox(3700,630,1670,288, bridge),
                                            new TilesHitBox(2500,360,340,175, banner_Hotel),
                                            new TilesHitBoxAnimated(1605,540,105, 276,bannerJaponezaverde,BANNER_JAPONEZA_VERDE),
                                            new TilesHitBoxAnimated(2005,535,54, 156,bannerCoke,BANNER_COKE),
                                            new TilesHitBoxAnimated(2310,590,76, 192,bannerJaponezamov,BANNER_JAPONEZA_MOV),
                                            new TilesHitBoxAnimated(2780,585,180, 65,bannerSushi,BANNER_SUSHI),
                                            new TilesHitBox(3200,530,298,288, smallBlock),
                                            new Guns(1800,450,96,96,secondGun),
                                            };


        this.hitBoxes[0].initHitbox(400,520,1550,288); // bridge
        this.hitBoxes[1].initHitbox(900,200,270,200); // bannerStart
        this.hitBoxes[2].initHitbox(2400,560,650,288); // tileLongBlock
        this.hitBoxes[3].initHitbox(0,0,0,0); // bannerR
        this.hitBoxes[4].initHitbox(0,0,0,0); // bannerLife
        this.hitBoxes[5].initHitbox(0,0,0,0); // bannerFlash
        this.hitBoxes[6].initHitbox(3300,670,1600,288); // bridge
        this.hitBoxes[7].initHitbox(0,0,0,0); // bannerHotel
        this.hitBoxes[8].initHitbox(0,0,0,0); // bannerJaponezaVerde
        this.hitBoxes[9].initHitbox(0,0,0,0); // bannerCoke
        this.hitBoxes[10].initHitbox(0,0,0,0); // bannerJaponezaMov
        this.hitBoxes[11].initHitbox(0,0,0,0); // bannerSushi
        this.hitBoxes[12].initHitbox(3230,560,250,250); // smallBlock
        this.hitBoxes[13].initHitbox(1800,450,32,32); // secondGun( the red one)

    }
    public  void drawTiles(Graphics g){
       for( TilesHitBox hitBox : hitBoxes){
           if (hitBox instanceof TilesHitBoxAnimated) {
               ((TilesHitBoxAnimated) hitBox).drawAnimated(g);
           }
           else if(hitBox instanceof Guns) {
               ((Guns) hitBox).drawGun(g);
           }
           else {
               hitBox.draw(g);
           }

       }
    }
    public void drawBG(Graphics2D g2d) {

        for (BackgroundLayer layer : backgroundLayers) {
            layer.draw(g2d);
        }

    }

    public void update(){
        camera.update();
        for (BackgroundLayer layer : backgroundLayers) {
            layer.update(camera);
        }
        for( TilesHitBox hitBox: hitBoxes) {
            hitBox.update(camera);
            if (hitBox instanceof TilesHitBoxAnimated) {
                ((TilesHitBoxAnimated) hitBox).updateAnimation();
            }
        }
    }

    public void LoadBannerStart(){
        banner_start = singleton.getAtlas(singleton.BANNER_START);

        this.bannerStart= new BufferedImage[4];
        for(int i=0; i<bannerStart.length;i++)
             {
                this.bannerStart[i] = this.banner_start.getSubimage(i * 270, 0, 270, 180);
            }
    }

    public void LoadBannerR(){
        banner_R = singleton.getAtlas(singleton.BANNER_R);

        this.bannerR= new BufferedImage[4];
        for(int i=0; i<bannerR.length;i++)
        {
            this.bannerR[i] = this.banner_R.getSubimage(i * 162, 0, 162, 195);
        }
    }

    public void LoadBannerFlash(){
        banner_flash = singleton.getAtlas(singleton.BANNER_FLASH);

        this.bannerFlash= new BufferedImage[4];
        for(int i=0; i<bannerFlash.length;i++)
        {
            this.bannerFlash[i] = this.banner_flash.getSubimage(i * 165, 0, 165, 204);
        }
    }

    public void LoadBannerLife(){
        banner_life = singleton.getAtlas(singleton.BANNER_LIFE);

        this.bannerLife= new BufferedImage[4];
        for(int i=0; i<bannerLife.length;i++)
        {
            this.bannerLife[i] = this.banner_life.getSubimage(i * 111, 0, 111, 180);
        }
    }
    public  void LoadBannerJaponezaVerde()
    {
        banner_Japoneza_verde = singleton.getAtlas(singleton.BANNER_JAPONEZA_VERDE);

        this.bannerJaponezaverde = new BufferedImage[4];
        for (int i = 0; i < bannerJaponezaverde.length; i++) {
            this.bannerJaponezaverde[i] = this.banner_Japoneza_verde.getSubimage(i * 105, 0, 105, 276);
        }
    }
    public  void LoadBannerCoke()
    {
        banner_coke = singleton.getAtlas(singleton.BANNER_COKE);

        this.bannerCoke = new BufferedImage[3];
        for (int i = 0; i < bannerCoke.length; i++) {
            this.bannerCoke[i] = this.banner_coke.getSubimage(i * 54, 0, 54, 156);
        }
    }

    public  void LoadBannerJaponezaMov()
    {
        banner_Japoneza_mov = singleton.getAtlas(singleton.BANNER_JAPONEZA_MOV);

        this.bannerJaponezamov = new BufferedImage[getSpriteAmountTiles(BANNER_JAPONEZA_MOV)];
        for (int i = 0; i < bannerJaponezamov.length; i++) {
            this.bannerJaponezamov[i] = this.banner_Japoneza_mov.getSubimage(i * 76, 0, 76, 192);
        }
    }

    public  void LoadBannerSushi()
    {
        banner_sushi = singleton.getAtlas(singleton.BANNER_SUSHI);

        this.bannerSushi = new BufferedImage[getSpriteAmountTiles(BANNER_SUSHI)];
        for (int i = 0; i < bannerSushi.length; i++) {
            this.bannerSushi[i] = this.banner_sushi.getSubimage(i * 180, 0, 180, 65);
        }
    }



}
