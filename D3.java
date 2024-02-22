package d3;

public class D3
{

    public static void main(String[] args)
    {
        //https://docs.oracle.com/javase/tutorial/uiswing/concurrency/initial.html
        javax.swing.SwingUtilities.invokeLater(() -> {
            Controller cntl = new Controller();
            cntl.showView();
        });
    }

}