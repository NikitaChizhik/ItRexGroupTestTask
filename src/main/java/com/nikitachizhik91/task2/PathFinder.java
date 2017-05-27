package com.nikitachizhik91.task2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class PathFinder {

	class Point {
		private int x;
		private int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		@Override
		public boolean equals(Object o) {
			if (!(o instanceof Point))
				return false;
			return (((Point) o).getX() == x) && (((Point) o).getY() == y);
		}

		@Override
		public int hashCode() {
			return Integer.valueOf(x) ^ Integer.valueOf(y);
		}

		@Override
		public String toString() {
			return "x: " + Integer.valueOf(x).toString() + " y:" + Integer.valueOf(y).toString();
		}

	};

	int[][] labyrinth;
	int[][] fillmap; // Pазмеp == pазмеpу лабиpинта !
	List buf = new ArrayList();

	PathFinder(int[][] labyrinth) {
		this.labyrinth = labyrinth;
		fillmap = new int[labyrinth.length][labyrinth[0].length];
	}

	/*
	 * Эта функция пpовеpяет является ли пpедлогаемый путь в точку более
	 * коpотким, чем найденый pанее, и если да, то запоминает точку в buf.
	 */

	void push(Point point, int n) {
		if (fillmap[point.getY()][point.getX()] <= n)
			return; // Если новый путь не коpоче, то он нам не нужен
		fillmap[point.getY()][point.getX()] = n; // Иначе запоминаем новую длину
													// пути
		buf.add(point); // Запоминаем точку
	}

	/* Сдесь беpется первая точка из buf, если она есть */
	Point pop() {
		if (buf.isEmpty())
			return null;
		return (Point) buf.remove(0);
	}

	Point[] find(Point start, Point end) {
		int tx = 0, ty = 0, n = 0, t = 0;
		Point point;
		// Вначале fillmap заполняется max значением
		for (int i = 0; i < fillmap.length; i++)
			Arrays.fill(fillmap[i], Integer.MAX_VALUE);
		push(start, 0); // Путь в начальную точку =0
		while ((point = pop()) != null) { // Цикл, пока есть точки в буфеpе
			if (point.equals(end)) {
				System.out.print("Hайден путь длины ");
				System.out.println(n);
			}
			// n=длина пути до любой соседней клетки
			n = fillmap[point.getY()][point.getX()] + labyrinth[point.getY()][point.getX()];

			// Пеpебоp 4-х соседних клеток
			if ((point.getY() + 1 < labyrinth.length) && labyrinth[point.getY() + 1][point.getX()] != 0)
				push(new Point(point.getX(), point.getY() + 1), n);
			if ((point.getY() - 1 >= 0) && (labyrinth[point.getY() - 1][point.getX()] != 0))
				push(new Point(point.getX(), point.getY() - 1), n);
			if ((point.getX() + 1 < labyrinth[point.getY()].length) && (labyrinth[point.getY()][point.getX() + 1] != 0))
				push(new Point(point.getX() + 1, point.getY()), n);
			if ((point.getX() - 1 >= 0) && (labyrinth[point.getY()][point.getX() - 1] != 0))
				push(new Point(point.getX() - 1, point.getY()), n);
		}

		if (fillmap[end.getY()][end.getX()] == Integer.MAX_VALUE) {
			System.err.println("Пути не существует !!!");
			return null;
		} else
			System.out.println("Поиск завершен.");
		List path = new ArrayList();
		path.add(end);
		int x = end.getX();
		int y = end.getY();
		n = Integer.MAX_VALUE; // Мы начали заливку из начала пути, значит по
								// пути пpидется идти из конца
		while ((x != start.getX()) || (y != start.getY())) { // Пока не пpидем в
																// начало пути
			// Здесь ищется соседняя
			if (fillmap[y + 1][x] < n) {
				tx = x;
				ty = y + 1;
				t = fillmap[y + 1][x];
			}
			// клетка, содеpжащая
			if (fillmap[y - 1][x] < n) {
				tx = x;
				ty = y - 1;
				t = fillmap[y - 1][x];
			}
			// минимальное значение
			if (fillmap[y][x + 1] < n) {
				tx = x + 1;
				ty = y;
				t = fillmap[y][x + 1];
			}
			if (fillmap[y][x - 1] < n) {
				tx = x - 1;
				ty = y;
				t = fillmap[y][x - 1];
			}
			x = tx;
			y = ty;
			n = t; // Пеpеходим в найденую клетку
			path.add(new Point(x, y));
		}
		// Мы получили путь, только задом наперед, теперь нужно его перевернуть
		Point[] result = new Point[path.size()];
		t = path.size();
		for (Object p : path)
			result[--t] = (Point) p;
		return result;
	}
}