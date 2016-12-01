package service;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pro2_flappy.game.GameBoard;
import pro2_flappy.game.Tile;
import pro2_flappy.game.tiles.WallTile;

public class CsvGameBoardLoader implements GameBoardLoader {

	private InputStream is;

	public CsvGameBoardLoader(InputStream is) {
		this.is = is;
	}

	@Override
	public GameBoard loadLevel() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			String[] line = br.readLine().split(";");
			int typeCount = Integer.parseInt(line[0]);
			
			Map<String,Tile> tileTipes = new HashMap<>();
			// radky definice dlazdic
			for (int i = 0; i < typeCount; i++) {
				line = br.readLine().split(";");
				String tileTipe = line[0];
				String clazz = line[1];
				int x = Integer.parseInt(line[2]);
				int y = Integer.parseInt(line[3]);
				int z = Integer.parseInt(line[4]);
				int w = Integer.parseInt(line[5]);
				String url = line[6];
				Tile tile = createTile(clazz,x,y,z,w,url);
				tileTipes.put(tileTipe, tile);
			}
			line = br.readLine().split(";");
			int rows = Integer.parseInt(line[0]);
			int columns = Integer.parseInt(line[1]);
			// vytvorime pole dlazdic odpovydajicich rozmeru
			Tile[][] tiles = new Tile[rows][columns];
			System.out.println(rows + "," + columns);
			for (int i = 0; i < rows; i++) {
				line = br.readLine().split(";");
				for (int j = 0; j < columns; j++) {
					String cell;
					if (j < line.length) {
						// bunka v csv existuje
						cell = line[j];
					} else {
						// bunka v csv chybi povazujeme ji za prazdnou
						cell = "";
					}
					//odpovidajici typ dlazdice hasMapy
						tiles[i][j] = tileTipes.get(cell);
				}
			}
			GameBoard gb = new GameBoard(tiles);
			return gb;
		} catch (IOException e) {
			throw new RuntimeException("Chyba pri cteni souboru", e);
		}
	}

	private Tile createTile(String clazz, int x, int y, int z, int w, String url) {
		
		return null;
	}

}
