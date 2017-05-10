package nz.ac.aut.ense701.gui;

import nz.ac.aut.ense701.gameModel.Game;
import nz.ac.aut.ense701.gameModel.Terrain;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameScreenPanel extends JPanel
{

	private static final Border normalBorder = new LineBorder(Color.BLACK, 1);
	private static final Border activeBorder = new LineBorder(Color.RED, 3);

	private final String SAND_IMAGE       = "resources/Sand_Tile.jpg";
	private final String FOREST_IMAGE     = "resources/Forest_Tile.jpg";
	private final String WETLAND_IMAGE    = "resources/Wetland_Tile.jpg";
	private final String SCRUB_IMAGE      = "resources/Scrub_Tile.jpg";
	private final String WATER_IMAGE      = "resources/Water_Tile.jpg";
	private final String UNEXPLORED_IMAGE = "resources/Unexplored_Tile.jpg";

	private JLabel lblText;
	private Game   game;
	private int    row;
	private int    column;

	/**
	 * Creates new GameScreenPanel.
	 *
	 * @param game   the game to represent
	 * @param row    the row to represent
	 * @param column the column to represent
	 */
	public GameScreenPanel(Game game, int row, int column)
	{
		this.game = game;
		this.row = row;
		this.column = column;
		initComponents();
	}

	/**
	 * Updates the representation of the grid square panel.
	 */
	public void update()
	{
		// get the GridSquare object from the world
		Terrain terrain = game.getTerrain(row, column);
		boolean squareVisible = game.isVisible(row, column);
		boolean squareExplored = game.isExplored(row, column);

		switch (terrain)
		{
			case SAND:
				lblText.setIcon(new ImageIcon(SAND_IMAGE));
				break;
			case FOREST:
				lblText.setIcon(new ImageIcon(FOREST_IMAGE));
				break;
			case WETLAND:
				lblText.setIcon(new ImageIcon(WETLAND_IMAGE));
				break;
			case SCRUB:
				lblText.setIcon(new ImageIcon(SCRUB_IMAGE));
				break;
			case WATER:
				lblText.setIcon(new ImageIcon(WATER_IMAGE));
				break;
			default:
				lblText.setIcon(new ImageIcon(UNEXPLORED_IMAGE));
				break;
		}

		if (squareExplored || squareVisible)
		{
			// Set the text of the JLabel according to the occupant
			lblText.setText("<html><font color='white'>" + game.getOccupantStringRepresentation(row, column)
					+ "</font></html>");
			if (game.getOccupantStringRepresentation(row, column).equals("D"))
			{
				Image icon = getScaledImage(new ImageIcon("resources/door.png").getImage(), 40, 40);
				lblText.setIcon(new ImageIcon(icon));
				lblText.setText("");
			}
			// set border colour according to playerBorder of not
			setBorder(game.hasPlayer(row, column) ? activeBorder : normalBorder);
		}
		else
		{
			lblText.setText("");
			lblText.setIcon(new ImageIcon(UNEXPLORED_IMAGE));
			setBorder(normalBorder);
		}
	}

	private Image getScaledImage(Image srcImg, int w, int h)
	{
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();

		return resizedImg;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	private void initComponents()
	{
		lblText = new JLabel();

		setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		setLayout(new BorderLayout());

		lblText.setFont(new java.awt.Font("Tahoma", 0, 18));
		lblText.setHorizontalAlignment(SwingConstants.CENTER);
		lblText.setText("");
		lblText.setOpaque(true);
		lblText.setHorizontalTextPosition(JLabel.CENTER);
		add(lblText, BorderLayout.CENTER);
	}

}
