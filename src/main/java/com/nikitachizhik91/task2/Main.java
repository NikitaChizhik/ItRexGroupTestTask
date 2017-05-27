package com.nikitachizhik91.task2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.nikitachizhik91.task2.PathFinder.Point;

//http://www.developersonthe.net/ru/posts/post_id/4-Algoritm-poiska-puti-v-labirinte-volnovoj-algoritm/
public class Main {
	public static void main(String[] args) {

		int[][] labyrinth = null;
		try (FileReader fileReader = new FileReader(
				"C:\\Users\\Dima\\git\\ItRexGroupTestTask\\ItRexGroupTestTask\\src\\main\\resources\\INPUT");
				BufferedReader reader = new BufferedReader(fileReader)) {

			labyrinth = readLabyrinth(reader);

		} catch (FileNotFoundException e) {
			// log+throw
		} catch (IOException e) {
			// log+throw
		}

		PathFinder pathFinder = new PathFinder(labyrinth);
		Point start = pathFinder.new Point(0, 1);// Hачальная точка
		Point end = pathFinder.new Point(4, 1);// Конечная точка
		Point[] path = pathFinder.find(start, end); // Hайдем путь
		System.out.println((path.length + 3) * 5);
	}

	private static int[][] readLabyrinth(BufferedReader reader) throws IOException {
		String line = reader.readLine();
		String[] properties = line.split(" ");
		int rows = Integer.valueOf(properties[1]);
		int columns = Integer.valueOf(properties[2]);

		int[][] labyrinth = new int[rows][columns];
		int row = 0;
		while ((line = reader.readLine()) != null) {
			if (line.isEmpty()) {
				row = 0;
			}
			if (line.trim().length() > 0) {
				int column = 0;
				char[] charArray = line.toCharArray();
				for (char ch : charArray) {
					ch = convertSymbol(ch);
					labyrinth[row][column] = ch;
					column++;
				}
				row++;
			}
		}
		return labyrinth;
	}

	private static char convertSymbol(char ch) {
		if (ch == 'o') {
			ch = '0';
		}
		if (ch == '1' || ch == '2' || ch == '.') {
			ch = '5';
		}
		return ch;
	}
}
