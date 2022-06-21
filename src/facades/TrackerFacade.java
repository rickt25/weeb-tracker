package facades;

import services.AnimeService;
import services.LightNovelService;
import services.MangaService;

// Berikut adalah Facade untuk mengumpulkan service-service yg berisi fungsi helper
// untuk berinteraksi dengan database.
public class TrackerFacade {
    private static TrackerFacade trackerFacade = new TrackerFacade();
    public AnimeService anime = new AnimeService();
    public MangaService manga = new MangaService();
    public LightNovelService lightNovel = new LightNovelService();

    // Singleton Design Pattern supaya penggunaan facade tidak berulang sehingga memory dapat digunakan
    // secara efektif
    public static TrackerFacade getInstance(){
        return trackerFacade;
    }


}
