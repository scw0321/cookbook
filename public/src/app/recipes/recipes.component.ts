import { Component, OnInit } from '@angular/core';
import { HttpService } from './../http.service';

@Component({
  selector: 'app-recipes',
  templateUrl: './recipes.component.html',
  styleUrls: ['./recipes.component.css']
})
export class RecipesComponent implements OnInit {

  searchIng: String;
  recipes: any;

  constructor(private _httpService: HttpService) { 
  }

  ngOnInit() {
    this.searchIng = "";
  }

  OnSearch(){
    console.log("in OnSearch(): this.searchIng = ",this.searchIng)
    let obs = this._httpService.apiSearch(this.searchIng);
    obs.subscribe(data =>{
      console.log("received api response: ", data)
      this.recipes = data['hits']
    })
  }
}
