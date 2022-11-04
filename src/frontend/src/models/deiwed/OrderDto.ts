export default class OrderDto {
  date: string = '';

  normalOption: {
  id: number;
  name: string;
  description: string;
  unitPrice: number;
  unitWeight: number;
  vegetarian: boolean;
  } = {
    id: 0,
    name: '',
    description: '',
    unitPrice: 0,
    unitWeight: 0,
    vegetarian: false
    };


  vegetarianOption: {
    id: number;
    name: string;
    description: string;
    unitPrice: number;
    unitWeight: number;
    vegetarian: boolean;
    } = {
      id: 0,
      name: '',
      description: '',
      unitPrice: 0,
      unitWeight: 0,
      vegetarian: false
      };
}