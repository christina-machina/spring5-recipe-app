package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {
    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveImageFile(Long recipeId, MultipartFile file) throws IOException {
        log.debug("Received a  file");

        Recipe recipe=recipeRepository.findById(recipeId).get();

        Byte[] byteObjects=new Byte[file.getBytes().length];

        int i=0;
        for (Byte b: file.getBytes()) {
            byteObjects[i++]=b;
        }
        recipe.setImage(byteObjects);
        recipeRepository.save(recipe);

    }
}
