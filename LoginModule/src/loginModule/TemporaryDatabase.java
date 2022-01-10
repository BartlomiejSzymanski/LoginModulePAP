package loginModule;
public class TemporaryDatabase{
    TemporaryUser [] users;

    public TemporaryDatabase(){
        this.users[0]= new TemporaryUser("Szymon", "Dyszewski", "admin", "okon");
        this.users[1]= new TemporaryUser("Karol", "Kasperek", "admin2", "ptakilatajakluczem");
        this.users[2]= new TemporaryUser("Bartek", "Szymanski", "admin3", "gdziestamtrzaslo");
    }


}