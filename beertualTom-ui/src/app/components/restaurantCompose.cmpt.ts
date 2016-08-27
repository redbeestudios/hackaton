import { Component, OnInit } from '@angular/core';
import { AppSettings } from '../configs/app.settings';
import { Product } from '../domain/product';
import { Restaurant } from '../domain/restaurant';



@Component({
  selector: 'restaurants',
  template:`
<div class="ui-container">
  <div class="row">
    <div class="col-md-10 col-md-offset-1">
      <h1 class="text-center">Nuevo Restaurant</h1>
      <form>
        <div class="modal-body">
          <div class="form-group">
            <div class="input-group">
              <span class="input-group-addon">Nombre del Restaurant:</span>
              <input type="text" class="form-control" >
            </div>
          </div>
          <div class="form-group">
            <h2 class="text-center">Agregar Productos</h2>
            <br>
            <div class="row">
              <div class="col-md-6">
                <div class="input-group">
                  <span class="input-group-addon">Nombre:</span>
                  <input type="text" class="form-control" [(ngModel)]="name">
                </div>
                <br>
                <div class="input-group">
                  <span class="input-group-addon">Tipo:</span>
                  <input type="text" class="form-control" [(ngModel)]="type">
                </div>
                <br>
                <button type="button" style="display: block; width: 100%;" class="btn btn-default" (click)="addProduct()">Agregar</button>
              </div>
              <div class="col-md-6">
                <p *ngIf="products.length == 0" class="text-center"><b>No hay productos.</b></p>
                <ul class="list-group">
                  <li *ngFor="let product of products" class="list-group-item">
                  <button class="btn btn-default btn-xs pull-right" type="button" (click)="removeProduct(product)" >Eliminar</button>
                    {{product.name}} ({{product.type}})
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default"><a routerLink="/notifications">Cancelar</a></button>
          <button type="submit" class="btn btn-danger" >Enviar</button>
        </div>
      </form>
    </div>
  </div>
</div>
    `,
  styles:[`
    .ui-container {
      border: solid 1px;
      border-radius: 5px;
      border-color: #A4A4A4;
      min-height: 200px;
    }
  `]
})
export class RestaurantComposeComponent implements OnInit{

  products:Product[];
  name:String;
  type:String;


  constructor() {  }

  ngOnInit(){
    this.products = [{name:'Muzza',type:"Pizza"},{name:'Fugazzeta',type:"Pizza"},{name:'Carne',type:"Empanada"}];
  }

  removeProduct(product){
    this.products.splice(this.products.indexOf(product),1);
  }

  addProduct(){
    let product:Product = new Product();
    product.name = this.name;
    product.type = this.type;
    this.products.push(product);
    this.name = "";
    this.type = "";
  }

  submitRestaurant(){

  }

}
