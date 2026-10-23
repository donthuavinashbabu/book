import re
from pathlib import Path
import fitz

# Configure logging
import logging
logging.basicConfig(
    level=logging.INFO,
    format="%(asctime)s - %(levelname)s - %(message)s"
)

# Extract the claim ID from the PDF file
def extract_claim_id(pdf_path: Path) -> str:
    match = re.search(r'CLM\d+', str(pdf_path), re.IGNORECASE)

    if match:
        return match.group(0).upper()
    
    return "UNKNOWN_CLAIM"

def extract_text_from_pdf(pdf_path: Path) -> str:
    text_by_page = []

    with fitz.open(pdf_path) as document:
        for page_number, page in enumerate(document, start=1):
            page_text = page.get_text()
            text_by_page.append(f"\n {page_number} ---\n{page_text}")

    return "\n".join(text_by_page).strip()

# Process PDFs
def process_pdfs(raw_dir: Path, processed_dir: Path) -> list[Path]:
    processed_dir.mkdir(parents=True, exist_ok=True)
    text_output_dir = processed_dir / "extracted_text"
    pdf_files = sorted(raw_dir.rglob("*.pdf"))
    output_files = []

    if not pdf_files:
        print(f"No PDF files found in {raw_dir}")
        return output_files
    
    for pdf_path in pdf_files:
        claim_id = extract_claim_id(pdf_path)
        claim_output_dir = text_output_dir/claim_id
        claim_output_dir.mkdir(parents=True, exist_ok=True)
        output_path = claim_output_dir / f"{pdf_path.stem}.txt"

        if output_path.exists():
            print(f"Skipping {pdf_path} because it already exists for claim {claim_id}")
            output_files.append(output_path)
            continue

        extracted_text = extract_text_from_pdf(pdf_path)
        output_path.write_text(extracted_text, encoding="utf-8")
        output_files.append(output_path)

        print(f"Processed {pdf_path.stem} for claim {claim_id}. Saved to {output_path}")

    return output_files

# Combine claim text into a single file
def combine_claim_text(processed_dir: Path) -> list[Path]:
    extracted_text_dir = processed_dir / "extracted_text"
    combined_files = []

    if not extracted_text_dir.exists():
        print(f"No extracted text directory found in {processed_dir}")
        return combined_files

    combined_output_dir = processed_dir / "combined_claims"
    combined_output_dir.mkdir(parents=True, exist_ok=True)

    for claim_dir in extracted_text_dir.iterdir():
        if claim_dir.is_dir():
            claim_id = claim_dir.stem
            output_path = combined_output_dir / f"{claim_id}.txt"
            
            if output_path.exists():
                print(f"Skipping {claim_id} because it already exists")
                combined_files.append(output_path)
                continue
            
            combined_parts = [f"Claim_ID: {claim_dir.name}"]
            text_files = sorted(claim_dir.rglob("*.txt"))
            for text_file in text_files:
                document_text = text_file.read_text(encoding="utf-8")
                combined_parts.append(
                    "\n".join(
                        [
                            "",
                            "=" * 80,
                            f"Document: {text_file.name}",
                            "=" * 80,
                            document_text
                        ]
                    )
                )
            
            combined_text = "\n".join(combined_parts).strip()
            output_path.write_text(combined_text, encoding="utf-8")
            combined_files.append(output_path)

            print(f"Combined {len(text_files)} documents for claim {claim_id}. Saved to {output_path}")
    
    return combined_files