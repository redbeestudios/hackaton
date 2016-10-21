import { Dish } from './dish'

export class Restaurant {
	id: string;
	name: string;
	dishes: Array<Dish>;

	constructor(id:string, name:string, dishes: Dish[]) {
		this.id = id;
		this.name = name;
		this.dishes = dishes;
	}
}
