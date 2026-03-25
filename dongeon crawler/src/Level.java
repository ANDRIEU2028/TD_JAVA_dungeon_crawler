public enum Level {
    LEVEL1(0,"./data/level1.txt",400,600),
    LEVEL2(1,"./data/level2.txt",790,600),
    LEVEL3(2,"./data/level3.txt",790,600);



    private int frameLineNumber;
    private String path;
    private int windowwidht;
    private int windowheight;


    Level(int frameLineNumber,String path , int windowwidht,int windowheight) {
        this.frameLineNumber = frameLineNumber;
        this.path=path;
        this.windowheight=windowheight;
        this.windowwidht=windowwidht;
    }
    public int getFrameLineNumber() {
        return frameLineNumber;
    }
    public String getPath() {
        return path;
    }
    public int getWindowwidht() {
        return windowwidht;
    }
    public int getWindowheight() {
        return windowheight;
    }

}
