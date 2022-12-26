package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Takım sayısını giriniz: ");
        int numTeams = scanner.nextInt();

        List<String>teams = new ArrayList<>();
        for (int i = 0; i<numTeams;i++){
            System.out.print((i+1)+" . takımın ismini giriniz: ");
            teams.add(scanner.next());
        }
        // Eğer girilen takım sayısı çift değilse, takımlara ilave olarak "Bay" eklenecek.
        if (teams.size()%2!=0){
            teams.add("Bay");
        }

        // Takımların kura torbasının karıştırılması
        Collections.shuffle(teams);

        //1. hafta fikstürü
        System.out.println("1. Hafta");
        for (int i = 0; i<teams.size()/2;i++){
            System.out.println(teams.get(i)+" vs. "+teams.get(teams.size()-i-1));
        }
        //2. hafta fikstürü
        System.out.println("2. Hafta");
        for (int i =0; i<teams.size()/2;i++){
            System.out.println(teams.get(teams.size()-i-1)+" vs. "+teams.get(i));
        }

    }
}