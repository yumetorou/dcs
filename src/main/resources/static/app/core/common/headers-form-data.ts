import { Headers } from '@angular/http';

export const contentHeadersFormData = new Headers();
contentHeadersFormData.append('Accept', 'application/json');
contentHeadersFormData.append('Content-Type', 'application/x-www-form-urlencoded');
