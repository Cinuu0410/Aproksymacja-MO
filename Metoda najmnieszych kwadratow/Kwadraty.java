import java.util.ArrayList;
import java.util.Scanner;

public class Kwadraty {
    ArrayList<Punkt> Punkty;
    double[] rozwiazania;
    double[][] pierwszatabelka;
    int m, n;
    double x;
    static double[] wierszX = {-1, -0.5, 0 , 0.5, 1};
    static double[] wierszY = new double[wierszX.length];
    Kwadraty(ArrayList<Punkt> Punkty, int m, double x) {
        pierwszatabelka = new double[m + 1][m + 1];
        rozwiazania = new double[m + 1];
        this.Punkty = Punkty;
        this.m = m;
        this.x = x;
        n = Punkty.size();
    }

    void liczPieTab() {
        for (int k = 0; k <= m; k++) {
            for (int j = 0; j <= m; j++) {
                for (int i = 0; i < Punkty.size(); i++) {
                    pierwszatabelka[k][j] += Math.pow(Punkty.get(i).x, k + j);
                }
                //System.out.println(k + "" + j + " " + pierwszatabelka[k][j]);
            }
        }
    }

    void liczRozwiazania() {
        for(int k = 0; k <= m; k++){
            for(int i = 0; i <= n - 1; i++){
                rozwiazania[k] += Math.pow(Punkty.get(i).x, k) * Punkty.get(i).y;
            }
        }

//        System.out.println("Sumy rozwiazan:");
//        for (int i = 0; i < rozwiazania.length; i++) {
//            System.out.println(rozwiazania[i]);
//        }
    }

    static void liczY(){
        double wynik = 0;
        for (int i = 0; i < wierszY.length; i++) {
            wynik = Math.sqrt(2 + 5 * Math.pow(wierszX[i], 2));
            wierszY[i] = wynik;
        }
//        for (int i = 0; i < wierszY.length; i++) {
//            System.out.println(wierszY[i]);
//        }
    }

    double liczWynik() {
        liczPieTab();
        liczRozwiazania();
        double wynik = 0;
        double[] gausswyniki = Gauss.rozwiaz(pierwszatabelka, rozwiazania);
//        System.out.println("Gauss wyniki:");
//        for (int i = 0; i < gausswyniki.length; i++) {
//            System.out.println(gausswyniki[i]);
//        }
        for (int i = 0; i <= m ; i++) {
            wynik += gausswyniki[i] * Math.pow(x, i);
        }
        return wynik;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Punkt> Punkty = new ArrayList<>();
        //Punkty z PDF
//        Punkt p1 = new Punkt(1,6);
//        Punkt p2 = new Punkt(2,19);
//        Punkt p3 = new Punkt(3,40);
//        Punkt p4 = new Punkt(4, 69);
//
//        Punkty.add(p1);
//        Punkty.add(p2);
//        Punkty.add(p3);
//        Punkty.add(p4);

        //Punkty z karteczki
        liczY();
        Punkt p1 = new Punkt(wierszX[0], wierszY[0]);
        Punkt p2 = new Punkt(wierszX[1], wierszY[1]);
        Punkt p3 = new Punkt(wierszX[2], wierszY[2]);
        Punkt p4 = new Punkt(wierszX[3], wierszY[3]);
        Punkt p5 = new Punkt(wierszX[4], wierszY[4]);
        Punkty.add(p1);
        Punkty.add(p2);
        Punkty.add(p3);
        Punkty.add(p4);
        Punkty.add(p5);

//        for(Punkt p : Punkty){
//            System.out.println(p.x + "  " + p.y);
//        }

        System.out.println("W jakim punkcie chcesz obliczyć rozwiązanie? Podaj x:");
        double x = scan.nextDouble();

        Kwadraty k1 = new Kwadraty(Punkty,2, x);
        System.out.println("Rozwiazanie to: " + k1.liczWynik());
    }
}