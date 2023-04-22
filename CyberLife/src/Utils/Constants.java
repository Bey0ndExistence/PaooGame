package Utils;

public class Constants {
    public static class PlayerConstants {
        public static final int IDLE = 0;
        public static final int RUN = 1;
        public static final int HURT = 2;
        public static final int DUCK = 3;
        public static final int SHOOT1 = 4;
        public static final int SHOOTcombuster = 5;
        public static final int JUMP = 6;

        public static final int IDLE_LEFT = 7;
        public static final int RUN_LEFT = 8;
        public static final int HURT_LEFT = 9;
        public static final int DUCK_LEFT = 10;
        public static final int SHOOT1_LEFT = 11;
        public static final int SHOOTcombuster_LEFT = 12;
        public static final int JUMP_LEFT = 13;

        public static final int BANNER_START = 14;

        public static final int BANNER_R = 15;
        public static final int BANNER_COKE = 16;
        public static final int BANNER_LIFE = 17;
        public static final int BANNER_FLASH = 18;
        public static final int BANNER_JAPONEZA_VERDE = 19;
        public static final int BANNER_JAPONEZA_MOV = 20;
        public static final int BANNER_SUSHI = 21;

        public static int GetSpriteAmount(int player_action) {

            switch (player_action) {
                case IDLE:
                    return 4;
                case RUN:
                    return 8;
                case HURT:
                    return 1;
                case DUCK:
                    return 5;
                case SHOOT1:
                    return 3;
                case SHOOTcombuster:
                    return 3;
                case JUMP:
                    return 7;
                case IDLE_LEFT:
                    return 4;
                case RUN_LEFT:
                    return 8;
                case HURT_LEFT:
                    return 1;
                case DUCK_LEFT:
                    return 5;
                case SHOOT1_LEFT:
                    return 3;
                case SHOOTcombuster_LEFT:
                    return 3;
                case JUMP_LEFT:
                    return 7;

                default:
                    return 4;
            }
        }

        public  static int getSpriteAmountTiles(int tile) {
            switch (tile) {
                case BANNER_START:
                    return 4;
                case BANNER_R:
                    return 4;
                case BANNER_COKE:
                    return 3;
                case BANNER_JAPONEZA_MOV:
                    return 4;
                case BANNER_SUSHI:
                    return 3;
                case BANNER_LIFE:
                    return 4;
                default:
                    return 4;
            }
        }
    }
}
