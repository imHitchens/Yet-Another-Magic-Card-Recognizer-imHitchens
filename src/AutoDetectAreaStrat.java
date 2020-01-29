import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

class AutoDetectAreaStrat extends AreaRecognitionStrategy {

    private ArrayList<MatchResult> results = new ArrayList<MatchResult>();
    ArrayList<ContourBoundingBox> bounds = new ArrayList<ContourBoundingBox>();

    @Override
    public ArrayList<MatchResult> recognize(BufferedImage in, RecognitionStrategy strat) {
        results.clear();
        bounds = CardBoundingBoxFinder.process(in);
        System.out.println(bounds.size());
        for (ContourBoundingBox bound : bounds) {
            System.out.println(bound);
            ImageDesc i = new ImageDesc(bound.getTransformedImage(in));
            MatchResult mr = strat.getMatch(i, SettingsPanel.RECOG_THRESH);
            if (mr != null) {
                results.add(mr);
            }
        }

        return results;
    }

    @Override
    public String getStratName() {
        return "auto-detect";
    }

    @Override
    public String getStratDisplayName() {
        return "Auto-Detect Card Bounds Within Area";
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void draw(Graphics g) {
        for(ContourBoundingBox bb : bounds)
        {
            bb.draw(RecogApp.INSTANCE.getCanvasGraphics());
        }
    }

    @Override
    public void init(int width, int height) {
    }

}