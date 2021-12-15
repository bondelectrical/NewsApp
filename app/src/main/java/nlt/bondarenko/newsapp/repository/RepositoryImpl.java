package nlt.bondarenko.newsapp.repository;

import java.io.IOException;

import nlt.bondarenko.newsapp.util.newsApi.models.response.SourceResponse;
import nlt.bondarenko.newsapp.util.newsApi.network.NetworkService;

public class RepositoryImpl implements Repository {

    @Override
    public SourceResponse getSourceList() throws IOException {
        return NetworkService.getSourceResponseApi().getSources().execute().body();
    }

//    public List<SourceListItem> getSourceList() {
//        List<SourceListItem> list = new ArrayList<>();
//        list.add(new SourceListItem("BBC NEWS", "BBC News — операционное подразделение компании Би-би-си (BBC), отвечающее за сбор и трансляцию новостей и текущих событий. Этот отдел является крупнейшей в мире вещательной новостной организацией и ежедневно транслирует около 120 часов теле- и радиовещания, а также освещает новости в Интернете."));
//        list.add(new SourceListItem("BBC1 NEWS", "BBC News — операционное подразделение компании Би-би-си (BBC), отвечающее за сбор и трансляцию новостей и текущих событий. Этот отдел является крупнейшей в мире вещательной новостной организацией и ежедневно транслирует около 120 часов теле- и радиовещания, а также освещает новости в Интернете."));
//        list.add(new SourceListItem("BBC2 NEWS", "BBC News — операционное подразделение компании Би-би-си (BBC), отвечающее за сбор и трансляцию новостей и текущих событий. Этот отдел является крупнейшей в мире вещательной новостной организацией и ежедневно транслирует около 120 часов теле- и радиовещания, а также освещает новости в Интернете."));
//        list.add(new SourceListItem("BBC3 NEWS", "BBC News — операционное подразделение компании Би-би-си (BBC), отвечающее за сбор и трансляцию новостей и текущих событий. Этот отдел является крупнейшей в мире вещательной новостной организацией и ежедневно транслирует около 120 часов теле- и радиовещания, а также освещает новости в Интернете."));
//        list.add(new SourceListItem("BBC4 NEWS", "BBC News — операционное подразделение компании Би-би-си (BBC), отвечающее за сбор и трансляцию новостей и текущих событий. Этот отдел является крупнейшей в мире вещательной новостной организацией и ежедневно транслирует около 120 часов теле- и радиовещания, а также освещает новости в Интернете."));
//
//        return list;
//    }
}
