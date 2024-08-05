export interface Fee {

  id: any;
  rollNo: string;
  studentName: string;
  departmentName: string;
  feesType: string;
  duration: string;
  collectionDate: any; // Assuming LocalDate is imported and available in your project
  paymentType: string;
  invoiceNumber?: string; // "?" denotes optional property
  status: string;
  amount: number;
  details: string;
}