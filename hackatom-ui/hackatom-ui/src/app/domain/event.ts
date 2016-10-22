import { Restaurant } from './restaurant';
import { Voter } from './voter';
import { Vote } from './vote';
import { Order } from './order';

export class Event {
  id: string;
  date: Date;
  state: string;
  restaurants: Array<Restaurant>;
  voters: Array<Voter>;
  chosenRestaurant: string;
  votes: Array<Vote>;
  orders:  Array<Order>;
}
