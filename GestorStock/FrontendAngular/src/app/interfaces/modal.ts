export enum modalStatus {
  ok = 'ok',
  cancel = 'cancel',
  success = 'success',
  error = 'error',
}

export interface IModalResponse {
  status: string
}