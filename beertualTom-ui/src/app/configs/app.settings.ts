export class AppSettings {
  // public static get API_ENDPOINT(): string { return 'https://hidden-falls-28563.herokuapp.com/api/'; }
  // public static get API_ENDPOINT(): string { return 'http://localhost:8080/api/'; }
  public static get API_ENDPOINT(): string { return '/'; } // esto lo agarra el proxy
  public  static get SSO_ENDPOINT(): string { return 'http://54.152.31.87:18000'; }
  //public  static get SSO_ENDPOINT(): string { return 'http://localhost:8000'; }

}
