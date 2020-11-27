import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Recipe } from '../recipe';

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.css'],
})
export class RecipeListComponent implements OnInit {
  @Output() recipeSelected: EventEmitter<Recipe> = new EventEmitter<Recipe>();
  selectedRecipe: Recipe;

  recipes: Array<Recipe> = new Array<Recipe>(
    new Recipe(
      'First Recipe',
      'Some description',
      'https://i2.wp.com/www.downshiftology.com/wp-content/uploads/2018/12/Shakshuka-19.jpg'
    ),
    new Recipe(
      'Second Recipe',
      'Another description',
      'https://img.taste.com.au/i6uNNUvx/taste/2018/02/mar-18_creamy-fettuccine-with-chicken-3000x2000-135690-1.jpg'
    )
  );

  constructor() {}

  ngOnInit(): void {}

  selectRecipe(recipe: Recipe) {
    this.selectedRecipe = recipe;
    this.recipeSelected.emit(recipe);
  }
}
