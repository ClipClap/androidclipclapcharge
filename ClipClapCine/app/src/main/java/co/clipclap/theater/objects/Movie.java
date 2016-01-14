package co.clipclap.theater.objects;

import android.os.Parcel;
import android.os.Parcelable;



/**
 * Created by josedavidmantilla on 1/13/16.
 */
public class Movie implements Parcelable {


    private int image;
    private int imageLarge;
    private String name;
    private String description;
    private String type;
    private int price;

    public Movie(int image,int imageLarge, String name, int price, String type, String description) {
        this.image = image;
        this.imageLarge = imageLarge;
        this.name = name;
        this.price = price;
        this.description= description;
        this.type=type;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }
    public int getImageLarge() {
        return imageLarge;
    }

    public String getDescription(){return description;}
    public String getType(){return type;}


    private Movie(Parcel in) {
        image = in.readInt();
        imageLarge= in.readInt();
        price = in.readInt();
        name = in.readString();
        description = in.readString();
        type = in.readString();
    }

    public int describeContents() {
        return 0;
    }



    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(image);
        out.writeInt(imageLarge);
        out.writeInt(price);
        out.writeString(name);
        out.writeString(description);
        out.writeString(type);
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
