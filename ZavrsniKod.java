package labos;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ZavrsniKod {

	
	private static int[] bojeVrhova;
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		System.out.println("Unesite ime datoteke: ");
		Scanner sc = new Scanner(System.in);
		String ime = sc.nextLine();

		File f = new File(ime);//ako izbacuje iznimku da ne moze naci graf.txt premjestite graf.txt u root direktorija,tocnije u folder gdje se nalazi src,bin,.settings,.classpath...
		
		String s;
		BufferedReader br = new BufferedReader(new FileReader(f));
		s=br.readLine();
		final int n = Integer.parseInt(s);
		
		int i=0,j;
		br.readLine();
		int[][] matrica = new int[n][n];
		int[][] matrica1 = new int[n][n];
		
		
		
		
		while((s=br.readLine()) != null) {
			String[] arr =  s.split(" ");			
				for(j=0;j<n;j++) {
					int x = Integer.parseInt(arr[j]);

					matrica[i][j] = x;
				}
				i++;
		}
		
		
		for(i=0;i<n;i++) {
			for(j=0;j<n;j++) {
				matrica1[i][j] = matrica[i][j];
			}
		}
		
		
		
		int[] stupnjevi = new int[n];
		int brr = 0;
		for(i=0;i<n;i++) {
			for(j=0;j<n;j++) {
				if(matrica[i][j]==1) {
					brr++;
				}
			}
			stupnjevi[i] = brr;
			brr=0;
		}
	
		int min;
		for(i=0;i<n;i++) {
			min=i;
			for(j=i+1;j<n;j++) {
				if(stupnjevi[j]>stupnjevi[min]) {
					min=j;
				}
			}
			int tmp=stupnjevi[i];
			stupnjevi[i] = stupnjevi[min];
			stupnjevi[min] = tmp;
		}
		
		
		
	/*	for(i=0;i<n;i++) {
			System.out.printf("%d ",stupnjevi[i]);
		}System.out.println();*/
	
		int[] poredakVrhova = new int[n];
		int pom=0;
		
		int i1;
		brr=0;
		boolean ide=true;
		int granica=n;
		for(i=0;i<n;i++) {
			i1=0;
			ide=true;
			//nadi onaj vrh koji je stponja stupnjevi[i]
			while(ide==true && i1 < granica) {
				for(j=0;j<n;j++) {
					if(matrica[i1][j]==1) {
						brr++;
					}
				}
				//System.out.println("brr->"+brr);
				if(stupnjevi[i]==brr) {
					for(int k=0;k<n;k++) {matrica[i1][k] =0;}
					poredakVrhova[pom] = i1;
					pom++;
					
					ide=false;}//jer je vrh i1 taj koji treba biti od stupnjevi[i]
				brr=0;
				i1++;
			}

		}
		
		
		/*for(i=0;i<pom;i++) {
			System.out.printf("%d ",poredakVrhova[i]);
		}System.out.println();*/
				
		bojeVrhova = new int[n];
		int boja = 1;
		for(i=0;i<n;i++) {
			if(sviObojani()) {break;}//ako su svi obojani stani
			if(bojeVrhova[poredakVrhova[i]] == 0) {	//ako vec nije obojan ga bojaj
				bojeVrhova[poredakVrhova[i]] = boja;
				//System.out.println("Vrh "+poredakVrhova[i]+" je obojan bojom "+boja);
					
				
				for(j=0;j<n;j++) {
					if(matrica1[poredakVrhova[i]][poredakVrhova[j]] == 0) { //trazimo sve one koji nisu spojeni s vrhom i
						if(bojeVrhova[poredakVrhova[j]] == 0) {	//nisu obojani
							if(akoSmijemBojatiTomBojom(matrica1,n,boja,poredakVrhova[j])) {
							
								bojeVrhova[poredakVrhova[j]] = boja;
						//		System.out.println("	Vrh "+poredakVrhova[j]+" je obojan bojom "+boja);
							}
						}
					}
				}
				boja++;
			}
				
		}
		
		
/*		System.out.println();
		for(i = 0; i< n;i++) {
			System.out.printf("%d ",bojeVrhova[i]);
		}System.out.println();*/
		
		
		int kromatski=0;
		for(i=0;i<n;i++) {
			if(bojeVrhova[i]>kromatski) {
				kromatski = bojeVrhova[i];
			}
		}
		
		
		
		//System.out.println();
		System.out.println(kromatski);
	
	}//kraj maina
	
	private static boolean sviObojani() {
		for(int i =0;i<bojeVrhova.length;i++) {
			if(bojeVrhova[i] == 0 ) {
				return false;
			}
		}
		return true;
	}
	
	
	
	private static boolean akoSmijemBojatiTomBojom(int[][] matrica1, int n, int boja,int vrh) {
		for(int j =0;j<n;j++) {
			if(matrica1[vrh][j] == 1) {
				if(bojeVrhova[j] == boja) {
					return false;
				}				
			}					
		}						
		return true;
	}
	
	

}
