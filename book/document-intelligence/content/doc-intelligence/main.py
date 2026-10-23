from dotenv import load_dotenv
from pathlib import Path
from src.ingestion.pdf_loader import process_pdfs, combine_claim_text
from src.preProcessing.text_cleaner import clean_combined_claims

# Configure logging
import logging
logging.basicConfig(
    level=logging.INFO,
    format="%(asctime)s - %(levelname)s - %(message)s"
)

def main():
    """
    Run document pipeline from PDFs to cleaned claim text
    """
    
    load_dotenv()
    logging.info("Starting document processing pipeline")

    project_root = Path(__file__).resolve().parent
    raw_dir = project_root / "data" / "raw"
    processed_dir = project_root / "data" / "processed"

    # Step 1: Process PDFs
    logging.info(f"Processing PDFs from {raw_dir}")
    output_files = process_pdfs(raw_dir=raw_dir, processed_dir=processed_dir)
    logging.info(f"Processed {len(output_files)} PDF files")

    # Step 2: Combine claim text
    logging.info(f"Combining claim text from {processed_dir / 'extracted_text'}")
    combined_files = combine_claim_text(processed_dir=processed_dir)
    logging.info(f"Combined {len(combined_files)} claim text files")

    # Step 3: Clean combined claim text
    logging.info(f"Cleaning combined claim text from {processed_dir / 'combined_claims'}")
    cleaned_files = clean_combined_claims(processed_dir=processed_dir)
    logging.info(f"Cleaned {len(cleaned_files)} claim text files")

    logging.info("Document processing pipeline completed successfully")

if __name__ == "__main__":
    main()