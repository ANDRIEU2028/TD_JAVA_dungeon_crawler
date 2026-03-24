import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;





public class Playground {
    private ArrayList<Sprite> environment = new ArrayList<>();
    public Playground (String pathName){
        try{
            BufferedImage tileSet = ImageIO.read(new File("./img/tileSetCompleted.png"));
            int size = 51;
            final Image imageArmorHigh = tileSet.getSubimage(9 * size, 13 * size, size, size);
            final Image imageArmorLow = tileSet.getSubimage(9 * size, 14 * size, size, size);
            final Image imagePont= tileSet.getSubimage(4 * size, 14 * size, size, size);
            final Image imageCanalHG= tileSet.getSubimage(4 * size, 0 * size, size, size);
            final Image imageCanalHD= tileSet.getSubimage(4 * size, 1 * size, size, size);
            final Image imageCanalBD= tileSet.getSubimage(4 * size, 2 * size, size, size);
            final Image imageCanalBG= tileSet.getSubimage(4 * size, 3 * size, size, size);
            final Image imageCanalCH= tileSet.getSubimage(3 * size, 14 * size, size, size);
            final Image imageCanalCV= tileSet.getSubimage(3 * size, 13 * size, size, size);
            final Image imageSol= tileSet.getSubimage(1 * size, 9 * size, size, size);
            final Image imagePilonne= tileSet.getSubimage(2 * size, 6 * size, size, size);
            final Image imageTree = ImageIO.read(new File("./img/tree.png"));
            final Image imageGrass = ImageIO.read(new File("./img/grass.png"));
            final Image imageRock = ImageIO.read(new File("./img/rock.png"));
            final Image imageTrap = ImageIO.read(new File("./img/trap.png"));
            final Image imageDownScale = ImageIO.read(new File("./img/trap.png"));
            final int imageTreeWidth = imageTree.getWidth(null);
            final int imageTreeHeight = imageTree.getHeight(null);
            final int imageGrassWidth = imageGrass.getWidth(null);
            final int imageGrassHeight = imageGrass.getHeight(null);
            final int imageRockWidth = imageRock.getWidth(null);
            final int imageRockHeight = imageRock.getHeight(null);
            final int imageTrapWidth = imageTrap.getWidth(null);
            final int imageTrapHeight = imageTrap.getHeight(null);
            final int imageDownScaleWidth = imageDownScale.getWidth(null);
            final int imageDownScaleHeight = imageDownScale.getHeight(null);
            final int imageArmorHighWidth = imageArmorHigh.getWidth(null);
            final int imageArmorHighHeight = imageArmorHigh.getHeight(null);
            final int imageArmorLowWidth = imageArmorLow.getWidth(null);
            final int imageArmorLowHeight = imageArmorLow.getHeight(null);
            final int imagePontWidth = imagePont.getWidth(null);
            final int imagePontHeight = imagePont.getHeight(null);
            final int imageCanalHGWidth = imageCanalHG.getWidth(null);
            final int imageCanalHGHeight = imageCanalHG.getHeight(null);
            final int imageCanalHDWidth = imageCanalHD.getWidth(null);
            final int imageCanalHDHeight = imageCanalHD.getHeight(null);
            final int imageCanalBGWidth = imageCanalBG.getWidth(null);
            final int imageCanalBGHeight = imageCanalBG.getHeight(null);
            final int imageCanalBDWidth = imageCanalBD.getWidth(null);
            final int imageCanalBDHeight = imageCanalBD.getHeight(null);
            final int imageCanalCHWidth = imageCanalCH.getWidth(null);
            final int imageCanalCHHeight = imageCanalCH.getHeight(null);
            final int imageCanalCVWidth = imageCanalCV.getWidth(null);
            final int imageCanalCVHeight = imageCanalCV.getHeight(null);
            final int imageSolWidth = imageSol.getWidth(null);
            final int imageSolHeight = imageSol.getHeight(null);
            final int imagePilonneWidth = imageSol.getWidth(null);
            final int imagePilonneHeight = imageSol.getHeight(null);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathName));
            String line=bufferedReader.readLine();
            int lineNumber = 0;
            int columnNumber = 0;
            while (line!= null){
                for (byte element : line.getBytes(StandardCharsets.UTF_8)){
                    switch (element){
                        case 'T' : environment.add(new SolidSprite(imageTree,columnNumber*imageTreeWidth,
                                lineNumber*imageTreeHeight, imageTreeWidth, imageTreeHeight));
                            break;
                        case ' ' : environment.add(new Sprite(imageGrass,columnNumber*imageGrassWidth,
                                lineNumber*imageGrassHeight,  imageGrassWidth, imageGrassHeight));
                            break;
                        case 'R' : environment.add(new SolidSprite(imageRock,columnNumber*imageRockWidth,
                                lineNumber*imageRockHeight,  imageRockWidth, imageRockHeight));
                            break;
                        case 'S' : environment.add(new StageSprite(imageTrap,columnNumber* imageTrapWidth,
                                lineNumber*imageTrapHeight, imageTrapWidth, imageTrapHeight));
                            break;
                        case 'P' : environment.add(new DamageSprite(imageTrap,columnNumber* imageDownScaleWidth,
                                lineNumber*imageDownScaleHeight, imageDownScaleWidth, imageDownScaleHeight));
                            break;
                        case 'H' :
                            environment.add(new HealSprite(imageArmorHigh, columnNumber * (1.3*imageArmorHighWidth-2),
                                    lineNumber * (1.3*imageArmorHighHeight-3), 1.36*imageArmorHighWidth,
                                    1.36*imageArmorHighHeight));
                            break;
                        case 'B' :
                            environment.add(new HealSprite(imageArmorLow, columnNumber * (1.3*imageArmorLowWidth-2),
                                    lineNumber * (1.3*imageArmorLowHeight-2), 1.36*imageArmorLowWidth,
                                    1.36*imageArmorLowHeight));
                            break;
                        case 'p' :
                            environment.add(new Sprite(imagePont, columnNumber * (1.3*imagePontWidth-2),
                                    lineNumber * (1.3*imagePontHeight-2), 1.36*imagePontWidth,
                                    1.36*imagePontHeight));
                            break;
                        case 'j' :
                            environment.add(new SolidSprite(imageCanalHG, columnNumber * (1.3*imageCanalHGWidth-2),
                                    lineNumber * (1.3*imageCanalHGHeight-2), 1.36*imageCanalHGWidth,
                                    1.36*imageCanalHGHeight));
                            break;
                        case 'k' :
                            environment.add(new SolidSprite(imageCanalHD, columnNumber * (1.3*imageCanalHDWidth-2),
                                    lineNumber * (1.3*imageCanalHDHeight-2), 1.36*imageCanalHDWidth,
                                    1.36*imageCanalHDHeight));
                            break;
                        case 'l' :
                            environment.add(new SolidSprite(imageCanalBD, columnNumber * (1.3*imageCanalBDWidth-2),
                                    lineNumber * (1.3*imageCanalBDHeight-2), 1.36*imageCanalBDWidth,
                                    1.36*imageCanalBDHeight));
                            break;
                        case 'm' :
                            environment.add(new SolidSprite(imageCanalBG, columnNumber * (1.3*imageCanalBGWidth-2),
                                    lineNumber * (1.3*imageCanalBGHeight-2), 1.36*imageCanalBGWidth,
                                    1.36*imageCanalBGHeight));
                            break;
                        case 'c' :
                            environment.add(new SolidSprite(imageCanalCH, columnNumber * (1.3*imageCanalCHWidth-2),
                                    lineNumber * (1.3*imageCanalCHHeight-2), 1.36*imageCanalCHWidth,
                                    1.36*imageCanalCHHeight));
                            break;
                        case 'C' :
                            environment.add(new SolidSprite(imageCanalCV, columnNumber * (1.3*imageCanalCVWidth-2),
                                    lineNumber * (1.3*imageCanalCVHeight-2), 1.36*imageCanalCVWidth,
                                    1.36*imageCanalCVHeight));
                            break;

                        case '$' :
                            environment.add(new Sprite(imageSol, columnNumber * (1.3*imageSolWidth-2),
                                    lineNumber * (1.3*imageSolHeight-2), 1.36*imageSolWidth,
                                    1.36*imageSolHeight));
                            break;
                        case 'W' :
                            environment.add(new SolidSprite(imagePilonne, columnNumber * (1.3*imagePilonneWidth-2),
                                    lineNumber * (1.3*imagePilonneHeight-2), 1.36*imagePilonneWidth,
                                    1.36*imagePilonneHeight));
                            break;
                    }

                    columnNumber++;
                }
                columnNumber =0;
                lineNumber++;
                line=bufferedReader.readLine();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public ArrayList<Sprite> getSolidSpriteList(){
        ArrayList <Sprite> solidSpriteArrayList = new ArrayList<>();
        for (Sprite sprite : environment){
            if (sprite instanceof SolidSprite) solidSpriteArrayList.add(sprite);
        }
        return solidSpriteArrayList;
    }
    public ArrayList<Sprite> getStageSpriteList() {
        ArrayList<Sprite> stageSpriteArrayList = new ArrayList<>();
        for (Sprite sprite : environment) {
            if (sprite instanceof StageSprite) stageSpriteArrayList.add(sprite);
        }
        return stageSpriteArrayList;
    }
    public ArrayList<Displayable> getSpriteList(){
        ArrayList <Displayable> displayableArrayList = new ArrayList<>();
        for (Sprite sprite : environment){
            displayableArrayList.add((Displayable) sprite);
        }
        return displayableArrayList;
    }
    public ArrayList<Sprite> getEnvironment() {
        return environment;
    }

}
