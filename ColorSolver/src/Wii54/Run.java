package Wii54;

import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.awt.*;
import java.util.Arrays;

/**
 * Created by Wii54 on 10/14/17.
 */
public class Run {
    private int level = 1;
    public void run(){
        /*ArrayList<ArrayList<ArrayList<Integer>>> pos = new ArrayList<ArrayList<ArrayList<Integer>>>();

        ArrayList<Integer> row1 = new ArrayList<Integer>(Arrays.asList(1,2,3));
        ArrayList<Integer> row2 = new ArrayList<Integer>(Arrays.asList(4,5,6));
        ArrayList<Integer> row3 = new ArrayList<Integer>(Arrays.asList(7,8,9));
        matrix.add(row1);
        matrix.add(row2);
        matrix.add(row3);

        //ArrayList<Integer> pos = new ArrayList<>();
        for (int i = 0; i < ; i ++){
            pos
        }
        */
        Robot robot;
        try {
            robot = new Robot();
            while (level < 150){

                int dim = Run.getDimension(level);
                ArrayList<Integer> boxSize = getBoxSize(dim);
                ArrayList<Integer> pos = getStartingPos(dim);

                ArrayList<ArrayList<Integer>> positions = calculateBoxPositions(pos,boxSize,dim);
                ArrayList<Color> boxColor = new ArrayList<>();

                for (int i = 0; i < positions.size(); i++){
                    int x = positions.get(i).get(0);
                    int y = positions.get(i).get(1);
                    //System.out.println("Starting");
                    //System.out.println("Pos " + x + ", " + y);
                    Color color = robot.getPixelColor(x,y);
                    boxColor.add(i, color);
                    //System.out.println(color);
                    //System.out.println("Ending");
                }

                int index = findTheOddOneOutBoxIndex(boxColor);
                int answerX = positions.get(index).get(0);
                int answerY = positions.get(index).get(1);
                robot.mouseMove(answerX,answerY);
                robot.mousePress(InputEvent.BUTTON1_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_MASK);
                if(level == 1) {
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                }

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                level++;
            }


        }catch (AWTException exception){
            exception.printStackTrace();
        }
    }

    private ArrayList<ArrayList<Integer>> calculateBoxPositions(ArrayList<Integer> startingPos, ArrayList<Integer> boxSize, Integer dimension){
        ArrayList<ArrayList<Integer>> positions = new ArrayList<>();
        int startingX = startingPos.get(0);
        int startingY = startingPos.get(1);
        int boxSizeX = boxSize.get(0);
        int boxSizeY = boxSize.get(1);
        for (int i = 0; i < dimension; i++){
            for (int j = 0; j < dimension; j++){
                ArrayList<Integer> pos = new ArrayList<>(Arrays.asList(startingX + boxSizeX * j, startingY + boxSizeY * i));
                positions.add(pos);
            }
        }
        return positions;
    }

    private static int getDimension(Integer lev){
        int dimension;
        if(lev < 2){
            dimension = 2;
        }else if(lev < 3){
            dimension = 3;
        }else if(lev < 4){
            dimension = 4;
        }else if(lev < 6){
            dimension = 5;
        }else if(lev < 8){
            dimension = 6;
        }else if(lev < 11){
            dimension = 7;
        }else if(lev < 17){
            dimension = 8;
        }else{
            dimension = 9;
        }
        return dimension;
    }

    private static ArrayList<Integer> getBoxSize(Integer dim){
        ArrayList<Integer> size = new ArrayList<>();
        dim--;
        switch (dim){
            case 1:
                size.add(0,250);
                size.add(1,250);
                break;
            case 2:
                size.add(0,167);
                size.add(1,167);
                break;
            case 3:
                size.add(0,125);
                size.add(1,125);
                break;
            case 4:
                size.add(0,100);
                size.add(1,100);
                break;
            case 5:
                size.add(0,85);
                size.add(1,85);
                break;
            case 6:
                size.add(0,72);
                size.add(1,72);
                break;
            case 7:
                size.add(0,62);
                size.add(1,62);
                break;
            case 8:
                size.add(0,55);
                size.add(1,55);
                break;
        }

        return size;
    }

    private static ArrayList<Integer> getStartingPos(Integer dim){
        ArrayList<Integer> size = new ArrayList<>();
        dim--;
        switch (dim){
            case 1:
                size.add(0,541);
                size.add(1,274);
                break;
            case 2:
                size.add(0,496);
                size.add(1,246);
                break;
            case 3:
                size.add(0,502);
                size.add(1,256);
                break;
            case 4:
                size.add(0,487);
                size.add(1,217);
                break;
            case 5:
                size.add(0,474);
                size.add(1,213);
                break;
            case 6:
                size.add(0,461);
                size.add(1,201);
                break;
            case 7:
                size.add(0,460);
                size.add(1,197);
                break;
            case 8:
                size.add(0,465);
                size.add(1,195);
                break;
        }
        return size;
    }

    private int findTheOddOneOutBoxIndex(ArrayList<Color> boxColor){
        //previous1 -> previous2 -> current
        /*
        Color previous1 = null;
        Color previous2 = null;
        int index = 0;

        for (int i = 0; i < boxColor.size(); i++){
            if (previous1 == null){
                previous1 = boxColor.get(i);
            }else if (previous2 == null){
                previous2 = previous1;
                previous1 = boxColor.get(i);
            }else {
                Color current = boxColor.get(i);
                ArrayList<Integer> a = new ArrayList<>(Arrays.asList(previous1.getRed(),previous1.getGreen(),previous1.getBlue(),previous1.getAlpha()));
                ArrayList<Integer> b = new ArrayList<>(Arrays.asList(previous2.getRed(),previous2.getGreen(),previous2.getBlue(),previous2.getAlpha()));
                ArrayList<Integer> c = new ArrayList<>(Arrays.asList(current.getRed(),current.getGreen(),current.getBlue(),current.getAlpha()));



                if (a.get(0) == b.get(0) && a.get(0) == c.get(0) && a.get(1) == b.get(1) && a.get(1) == c.get(1) && a.get(2) == b.get(2) && a.get(2) == c.get(2) && a.get(3) == b.get(3) && a.get(3) == c.get(3)){

                }

                if (!(current.equals(previous1) && current.equals(previous2))){
                    //find the different color
                    if (current.equals(previous1)){
                        if(current.equals(previous2)){
                            index = 10;
                            break;
                        }else {
                            index = i - 1;
                            break;
                        }
                    }else{
                        if(current.equals(previous2)) {
                            index = i - 2;
                            break;
                        }else{
                            index = i;
                            break;
                        }
                    }
                }

            }
        }*/
        Color previous1 = null;
        Color previous2 = null;
        int index = 10;

        for (int i = 0; i < boxColor.size(); i++){
            if (previous1 == null){
                previous1 = boxColor.get(i);
            }else if (previous2 == null){
                previous2 = previous1;
                previous1 = boxColor.get(i);
            }else {
                Color current = boxColor.get(i);
                if (!(current.equals(previous1) && current.equals(previous2))){
                    //find the different color
                    if (current.equals(previous1)){
                        if(current.equals(previous2)){
                            index = 10;
                            break;
                        }else {
                            index = i - 2;
                            break;
                        }
                    }else{
                        if(current.equals(previous2)) {
                            index = i - 1;
                            break;
                        }else{
                            index = i;
                            break;
                        }
                    }
                }
            }

        }
        return index;
    }
}
