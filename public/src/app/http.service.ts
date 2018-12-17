import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor( private _http:HttpClient) { 
  }
  
      apiSearch(ingredient){
        return this._http.get("https://api.edamam.com/search?q="+ingredient+"&app_id=659e59e4&app_key=fa04c651c0c6cfe0d7ac0216c4742e2a&from=0&to=3&calories=591-722&health=alcohol-free");
      }
}
