/**
 *
 */

public class Main {
    public static void main(String[] args) {
        try {
            Shape s;
            s = (Shape) new Rectangle(2, 1);
            System.out.println("The area of " + s + " is " + s.getArea());
            s = (Shape) new Rectangle(-2, 8);
            System.out.println("The perimeter of " + s +
                    " is " + s.getPerimeter());
            s = (Shape) new Circle(-1);
            System.out.println("The perimeter of " + s +
                    " is " + s.getPerimeter());
            s = (Shape) new Circle(2);
            System.out.println("The area of " + s + " is " + s.getArea());
            Identifiable i = (Identifiable)s;
            if(i != null) {
                System.out.println("The last shape ID: " + i.getId());
            }
        }
        catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}