import java.util.TimerTask;

/* A class holding the procedure for updating the view each frame to match
 * the model*/
public class UpdateViewTask extends TimerTask{
    private DrawPanel canvasPanel;
    public int frameCount;

    public UpdateViewTask(DrawPanel panelToUpdate){
        super();
        frameCount = 0;

        canvasPanel = panelToUpdate;
    }

    public void run(){
        /*System.out.println("Frame update " + frameCount);
        frameCount++;*/

        /*Update the view to match the model, called once per frame*/
        canvasPanel.updateEntityCount();
        canvasPanel.recomputeAiEntities();
        canvasPanel.revalidate();
        canvasPanel.repaint();
    }
}
