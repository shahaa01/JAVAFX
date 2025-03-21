import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CardPane extends Pane {

    private static Image club, heart, spade, diamond;
    private static final double ASPECT_RATIO = 635.0 / 889.0;

    private StringProperty cardStr;
    private double cardWidth, cardHeight;

    // Load suit images once in a static block
    static {
        try {
            club = new Image("file:/Users/aadityashah/Documents/FXJAVA/FXfun/src/black.png");
            heart = new Image("file:/Users/aadityashah/Documents/FXJAVA/FXfun/src/blue.png");
            spade = new Image("file:/Users/aadityashah/Documents/FXJAVA/FXfun/src/green.png");
            diamond = new Image("file:/Users/aadityashah/Documents/FXJAVA/FXfun/src/pink.png");

            // Check if images loaded correctly
            if (club.isError()) {
                System.err.println("Error loading club image");
            }
            if (heart.isError()) {
                System.err.println("Error loading heart image");
            }
            if (spade.isError()) {
                System.err.println("Error loading spade image");
            }
            if (diamond.isError()) {
                System.err.println("Error loading diamond image");
            }
        } catch (Exception e) {
            System.err.println("Error loading images: " + e.getMessage());
        }
    }

    // Constructor
    public CardPane(StringProperty cardStr) {
        super();
        this.cardStr = cardStr;
        setPrefSize(635, 889);

        // Add listener to cardStr
        cardStr.addListener((observableValue, oldValue, newValue) -> changeCard());

        // Add listeners to dynamically resize card
        widthProperty().addListener((obs, oldVal, newVal) -> resizeCard());
        heightProperty().addListener((obs, oldVal, newVal) -> resizeCard());
    }

    // Change card based on cardStr
    private void changeCard() {
        getChildren().clear();

        // Validate card string
        if (!isValidCard(cardStr.get())) {
            return;
        }

        // Draw card and symbols
        drawCard();
    }

    // Validate if the card is valid
    private boolean isValidCard(String card) {
        if (card == null || card.length() != 2) return false;
        char rank = Character.toUpperCase(card.charAt(0));
        char suit = Character.toUpperCase(card.charAt(1));
        return "A23456789TJQK".indexOf(rank) != -1 && "CHSD".indexOf(suit) != -1;
    }

    // Draw card with grid and suits
    private void drawCard() {
        cardWidth = getWidth();
        cardHeight = getHeight();

        // Create card background
        Rectangle cardBackground = createCardBackground();
        getChildren().add(cardBackground);

        // Get rank and suit
        char rank = Character.toUpperCase(cardStr.get().charAt(0));
        char suit = Character.toUpperCase(cardStr.get().charAt(1));

        // Get suit image
        Image suitImage = getSuitImage(suit);

        // Add suit symbols to grid
        addSuitSymbols(rank, suitImage);
    }

    // Create card background
    private Rectangle createCardBackground() {
        Rectangle rect = new Rectangle(cardWidth, cardHeight, Color.WHITE);
        rect.setArcWidth(20);
        rect.setArcHeight(20);
        rect.setStroke(Color.DARKGREEN); // Optional border to match design
        rect.setStrokeWidth(5);
        return rect;
    }

    // Get suit image
    private Image getSuitImage(char suit) {
        switch (suit) {
            case 'C': return club;
            case 'H': return heart;
            case 'S': return spade;
            case 'D': return diamond;
            default: return null;
        }
    }

    // Add suit symbols based on rank
    private void addSuitSymbols(char rank, Image suitImage) {
        int rankValue = getRankValue(rank);

        // Create grid with suit symbols (createGrid handles adding symbols now)
        GridPane grid = createGrid(rankValue, suitImage);

        // Add grid with some margin and padding for better look
        grid.setTranslateX(cardWidth * 0.05);
        grid.setTranslateY(cardHeight * 0.05);
        getChildren().add(grid); // Add the grid to the card
    }

    // Get numerical value of rank
    private int getRankValue(char rank) {
        if (rank == 'A') return 1;
        if (rank >= '2' && rank <= '9') return rank - '0';
        if (rank == 'T') return 10;
        if (rank == 'J') return 11;
        if (rank == 'Q') return 12;
        if (rank == 'K') return 13;
        return 0;
    }

    // Create grid for suits
    private GridPane createGrid(int rankValue, Image suitImage) {
        GridPane grid = new GridPane();
        grid.setHgap(cardWidth * 0.08);  // Increase horizontal spacing
        grid.setVgap(cardHeight * 0.05); // Increase vertical spacing
        grid.setAlignment(Pos.CENTER);

        // Padding to match the cleaner look
        double paddingX = cardWidth * 0.1;  // 10% padding horizontally
        double paddingY = cardHeight * 0.1; // 10% padding vertically
        grid.setTranslateX(paddingX);
        grid.setTranslateY(paddingY);

        // Always display in 3 columns
        int cols = 3;
        int rows = (int) Math.ceil((double) rankValue / cols);

        // Add correct number of symbols
        for (int i = 0; i < rankValue; i++) {
            ImageView suitView = createSuitImageView(suitImage);
            grid.add(suitView, i % cols, i / cols);
        }

        return grid;
    }

    // Create suit ImageView
    private ImageView createSuitImageView(Image suitImage) {
        ImageView suitView = new ImageView(suitImage);
        suitView.setFitHeight(cardHeight / 8);  // Smaller size to fit cleaner
        suitView.setPreserveRatio(true);
        return suitView;
    }

    // Resize card dynamically
    private void resizeCard() {
        double newWidth = getWidth();
        double newHeight = newWidth / ASPECT_RATIO;

        if (newHeight > getHeight()) {
            newHeight = getHeight();
            newWidth = newHeight * ASPECT_RATIO;
        }

        setPrefSize(newWidth, newHeight);
        changeCard(); // Redraw with new size
    }
}
