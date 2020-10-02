package domain.category.model

import data.category.model.CategoryItem

fun CategoryItem.toObject(): Category {
    return Category(
        categoryId= categoryId,
        categoryType = categoryType.orEmpty(),
        categoryTitle = categoryTitle.orEmpty(),
        categoryShortDescription= categoryShortDescription.orEmpty(),
        categoryLongDescription = categoryLongDescription.orEmpty(),
        active = active.orEmpty(),
        slug = slug.orEmpty(),
        langId = langId.orEmpty(),
        createdAt = createdAt.orEmpty()
    )
}