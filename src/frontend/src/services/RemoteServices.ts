import AttendeeDto from '@/models/deiwed/AttendeeDto';
import SessionDto from '@/models/deiwed/SessionDto';
import DishDto from '@/models/deiwed/DishDto';
import DeiwedError from '@/models/error/DeiwedError';
import axios from 'axios';

const httpClient = axios.create();
const headers = {
  'Authorization': 'Bearer ist198959'
}
httpClient.defaults.timeout = 50000;
httpClient.defaults.baseURL = process.env.VUE_APP_ROOT_API;
httpClient.defaults.headers.post['Content-Type'] = 'application/json';

export default class RemoteServices {
  static async getAttendees(): Promise<AttendeeDto[]> {
    return httpClient
      .get('/attendees')
      .then((response) => response.data)
      .catch(async (error) => {
        throw new DeiwedError(
          await this.errorMessage(error),
          error.response.data.code
        );
      });
  }

  static async createAttendee(): Promise<AttendeeDto[]> {
    return httpClient
      .post('/attendees')
      .then((response) => response.data)
      .catch(async (error) => {
        throw new DeiwedError(
          await this.errorMessage(error),
          error.response.data.code
        );
      });
  }

  static async getSessions(): Promise<SessionDto[]> {
    return httpClient
      .get('/sessions')
      .then((response) => response.data)
      .catch(async (error) => {
        throw new DeiwedError(
          await this.errorMessage(error),
          error.response.data.code
        );
      });
  }

  static async getDishes(): Promise<DishDto[]> {
    return httpClient
      .get('https://eindhoven.rnl.tecnico.ulisboa.pt/food-store/api/v1/dishes', {headers})
      .then(response => (response.data))
      .catch(async (error) => {
        throw new DeiwedError(
          await this.errorMessage(error),
          error.response.data.code
        );
      });
  }

  static async errorMessage(error: any): Promise<string> {
    if (error.message === 'Network Error') {
      return 'Unable to connect to server';
    } else if (error.message.split(' ')[0] === 'timeout') {
      return 'Request timeout - Server took too long to respond';
    } else if (error.response?.data?.message) {
      return error.response.data.message;
    } else {
      return 'Unknown Error - Contact admin';
    }
  }
}
